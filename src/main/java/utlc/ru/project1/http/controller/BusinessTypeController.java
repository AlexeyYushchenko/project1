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
import utlc.ru.project1.dto.businesstype.BusinessTypeCreateUpdateDto;
import utlc.ru.project1.service.BusinessTypeService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/businessTypes")
public class BusinessTypeController {

    private final BusinessTypeService businessTypeService;

    @GetMapping
    public String findAll(Model model) {
        var businessTypes = businessTypeService.findAll();
        model.addAttribute("businessTypes", businessTypes);
        return "businessType/businessTypes";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id,
                           Model model) {
        return businessTypeService.findById(id)
                .map(businessType -> {
                    model.addAttribute("businessType", businessType);
                    return "businessType/businessType";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("businessType") BusinessTypeCreateUpdateDto createDto) {
        model.addAttribute("businessType", createDto);
        return "businessType/create";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated BusinessTypeCreateUpdateDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()){
            try {
                businessTypeService.create(dto);
                return "redirect:/businessTypes";
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.businessType.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("businessType", dto);
        return "redirect:/businessTypes/create";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated BusinessTypeCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()) {
            try {
                return businessTypeService.update(id, createUpdateDto)
                        .map(it -> "redirect:/businessTypes/{id}")
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.businessType.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("businessType", createUpdateDto);
        return "redirect:/businessTypes/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!businessTypeService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/businessTypes";
    }
}