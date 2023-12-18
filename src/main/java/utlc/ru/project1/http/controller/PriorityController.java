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
import utlc.ru.project1.dto.priority.PriorityCreateUpdateDto;
import utlc.ru.project1.service.PriorityService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/priorities")
public class PriorityController {

    private final PriorityService priorityService;

    @GetMapping
    public String findAll(Model model, ModelAndView modelAndView) {
        var priorities = priorityService.findAll();
        model.addAttribute("priorities", priorities);
        return "priority/priorities";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id,
                           Model model) {
        return priorityService.findById(id)
                .map(priority -> {
                    model.addAttribute("priority", priority);
                    return "priority/priority";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("priority") PriorityCreateUpdateDto createDto) {
        model.addAttribute("priority", createDto);
        model.addAttribute("roles", Role.values());
        return "priority/create";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated PriorityCreateUpdateDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()){
            try {
                priorityService.create(dto);
                return "redirect:/priorities";
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.priority.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("priority", dto);
        return "redirect:/priorities/create";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated PriorityCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("priority", createUpdateDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/priorities/" + id;
        }

        return priorityService.update(id, createUpdateDto)
                .map(it -> "redirect:/priorities/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!priorityService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/priorities";
    }

}
