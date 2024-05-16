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
import utlc.ru.project1.dto.currency.CurrencyCreateUpdateDto;
import utlc.ru.project1.service.CurrencyService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public String findAll(Model model) {
        var currencies = currencyService.findAll();
        model.addAttribute("currencies", currencies);
        return "currency/currencies";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        return currencyService.findById(id)
                .map(currency -> {
                    model.addAttribute("currency", currency);
                    return "currency/currency";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String createForm(Model model, @ModelAttribute("currency") CurrencyCreateUpdateDto createDto) {
        model.addAttribute("currency", createDto);
        return "currency/create";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated CurrencyCreateUpdateDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasErrors()) {
            try {
                currencyService.create(dto);
                return "redirect:/currencies";
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "An error occurred with the database");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("currency", dto);
        return "redirect:/currencies/create";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated CurrencyCreateUpdateDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasErrors()) {
            try {
                return currencyService.update(id, dto)
                        .map(it -> "redirect:/currencies/{id}")
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "An error occurred with the database");
            }
        }
        redirectAttributes.addFlashAttribute("currency", dto);
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        return "redirect:/currencies/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!currencyService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/currencies";
    }

}
