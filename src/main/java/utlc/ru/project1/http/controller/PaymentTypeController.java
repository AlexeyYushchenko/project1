package utlc.ru.project1.http.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import utlc.ru.project1.dto.paymenttype.PaymentTypeCreateUpdateDto;
import utlc.ru.project1.service.PaymentTypeService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/paymentTypes")
public class PaymentTypeController {

    private final PaymentTypeService paymentTypeService;

    @GetMapping
    public String findAll(Model model) {
        var paymentTypes = paymentTypeService.findAll();
        model.addAttribute("paymentTypes", paymentTypes);
        return "paymentType/paymentTypes";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id,
                           Model model) {
        return paymentTypeService.findById(id)
                .map(paymentType -> {
                    model.addAttribute("paymentType", paymentType);
                    return "paymentType/paymentType";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("paymentType") PaymentTypeCreateUpdateDto createDto) {
        model.addAttribute("paymentType", createDto);
        return "paymentType/create";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated PaymentTypeCreateUpdateDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()) {
            try {
                paymentTypeService.create(dto);
                return "redirect:/paymentTypes";
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.paymentType.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("paymentType", dto);
        return "redirect:/paymentTypes/create";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated PaymentTypeCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()) {
            try {
                return paymentTypeService.update(id, createUpdateDto)
                        .map(it -> "redirect:/paymentTypes/{id}")
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.paymentType.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("paymentType", createUpdateDto);
        return "redirect:/paymentTypes/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!paymentTypeService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/paymentTypes";
    }
}