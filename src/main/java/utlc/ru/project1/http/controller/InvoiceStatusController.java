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
import utlc.ru.project1.database.entity.Role;
import utlc.ru.project1.dto.invoicestatus.InvoiceStatusCreateUpdateDto;
import utlc.ru.project1.service.InvoiceStatusService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/invoiceStatuses")
public class InvoiceStatusController {

    private final InvoiceStatusService invoiceStatusService;

    @GetMapping
    public String findAll(Model model, ModelAndView modelAndView) {
        var invoiceStatuses = invoiceStatusService.findAll();
        model.addAttribute("invoiceStatuses", invoiceStatuses);
        return "invoiceStatus/invoiceStatuses";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id,
                           Model model) {
        return invoiceStatusService.findById(id)
                .map(invoiceStatus -> {
                    model.addAttribute("invoiceStatus", invoiceStatus);
                    return "invoiceStatus/invoiceStatus";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("invoiceStatus") InvoiceStatusCreateUpdateDto createDto) {
        model.addAttribute("invoiceStatus", createDto);
        model.addAttribute("roles", Role.values());
        return "invoiceStatus/create";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated InvoiceStatusCreateUpdateDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()){
            try {
                invoiceStatusService.create(dto);
                return "redirect:/invoiceStatuses";
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.invoiceStatus.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("invoiceStatus", dto);
        return "redirect:/invoiceStatuses/create";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated InvoiceStatusCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("invoiceStatus", createUpdateDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/invoiceStatuses/" + id;
        }

        return invoiceStatusService.update(id, createUpdateDto)
                .map(it -> "redirect:/invoiceStatuses/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!invoiceStatusService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/invoiceStatuses";
    }

}