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
import utlc.ru.project1.dto.industrytype.IndustryTypeCreateUpdateDto;
import utlc.ru.project1.service.IndustryTypeService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/industryTypes")
public class IndustryTypeController {

    private final IndustryTypeService industryTypeService;

    @GetMapping
    public String findAll(Model model, ModelAndView modelAndView) {
        var industryTypes = industryTypeService.findAll();
        model.addAttribute("industryTypes", industryTypes);
        return "industryType/industryTypes";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id,
                           Model model) {
        return industryTypeService.findById(id)
                .map(industryType -> {
                    model.addAttribute("industryType", industryType);
                    return "industryType/industryType";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute @Validated IndustryTypeCreateUpdateDto industryTypeCreateUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("industryType", industryTypeCreateUpdateDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/industryTypes";
        }
        industryTypeService.create(industryTypeCreateUpdateDto);
        return "redirect:/industryTypes";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated IndustryTypeCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("industryType", createUpdateDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/industryTypes/" + id;
        }

        return industryTypeService.update(id, createUpdateDto)
                .map(it -> "redirect:/industryTypes/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!industryTypeService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/industryTypes";
    }

}

