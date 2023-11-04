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
import utlc.ru.project1.dto.priority.PriorityReadDto;
import utlc.ru.project1.dto.priority.PriorityUpdateDto;
import utlc.ru.project1.service.PriorityService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/priorities")
public class PriorityController {

    private final PriorityService priorityService;

    @GetMapping
    public String findAll(Model model, ModelAndView modelAndView) {
        var priorityes = priorityService.findAll();
        model.addAttribute("priorities", priorityes);
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

    @PostMapping
    public String create(@ModelAttribute @Validated PriorityReadDto priorityReadDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("priority", priorityReadDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/priorities";
        }
        priorityService.create(priorityReadDto);
        return "redirect:/priorities";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated PriorityUpdateDto updatePriority,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("priority", updatePriority);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/priorities/" + id;
        }

        return priorityService.update(id, updatePriority)
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
