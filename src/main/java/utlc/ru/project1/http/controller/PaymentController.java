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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import utlc.ru.project1.dto.manufacturer.ManufacturerCreateUpdateDto;
import utlc.ru.project1.dto.payment.PaymentCreateUpdateDto;
import utlc.ru.project1.dto.payment.PaymentReadDto;
import utlc.ru.project1.service.ClientService;
import utlc.ru.project1.service.CurrencyService;
import utlc.ru.project1.service.PaymentService;
import utlc.ru.project1.service.PaymentTypeService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final ClientService clientService;
    private final CurrencyService currencyService;
    private final PaymentTypeService paymentTypeService;

    @GetMapping
    public String findAll(Model model) {
        var payments = paymentService.findAll();
        model.addAttribute("payments", payments);
        return "payment/payments";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return paymentService.findById(id)
                .map(payment -> {
                    model.addAttribute("payment", payment);
                    model.addAttribute("currencies", currencyService.findAll());
                    model.addAttribute("clients", clientService.findAll());
                    model.addAttribute("paymentTypes", paymentTypeService.findAll());
                    return "payment/payment";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("payment") PaymentCreateUpdateDto dto) {
        model.addAttribute("payment", dto);
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("currencies", currencyService.findAll());
        return "payment/create";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated PaymentCreateUpdateDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasErrors()) {
            try {
                paymentService.create(dto);
                return "redirect:/payments";
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.payment.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("payment", dto);
        return "redirect:/payments/create";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute @Validated PaymentCreateUpdateDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasErrors()) {
            try {
                return paymentService.update(id, dto)
                        .map(it -> "redirect:/payments/{id}")
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.payment.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("payment", dto);
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        return "redirect:/payments/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!paymentService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/payments";
    }
}