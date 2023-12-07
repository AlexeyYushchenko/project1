package utlc.ru.project1.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import utlc.ru.project1.dto.businesstype.BusinessTypeCreateUpdateDto;
import utlc.ru.project1.service.BusinessTypeService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/businessTypes")
public class BusinessTypeController {

    private final BusinessTypeService businessTypeService;

    @GetMapping
    public String findAll(Model model, ModelAndView modelAndView) {
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

    @PostMapping
    public String create(@ModelAttribute @Validated BusinessTypeCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("businessType", createUpdateDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/businessTypes";
        }
        businessTypeService.create(createUpdateDto);
        return "redirect:/businessTypes";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated BusinessTypeCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("businessType", createUpdateDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/businessTypes/" + id;
        }

        return businessTypeService.update(id, createUpdateDto)
                .map(it -> "redirect:/businessTypes/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!businessTypeService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/businessTypes";
    }
}