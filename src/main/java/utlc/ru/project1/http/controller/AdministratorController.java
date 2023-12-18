package utlc.ru.project1.http.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import utlc.ru.project1.database.entity.Role;
import utlc.ru.project1.dto.administrator.AdministratorCreateUpdateDto;
import utlc.ru.project1.service.AdministratorService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/administrators")
public class AdministratorController {

    private final AdministratorService administratorService;

    @GetMapping
    public String findAll(Model model, ModelAndView modelAndView) {
        var administrators = administratorService.findAll();
        model.addAttribute("administrators", administrators);
        return "administrator/administrators";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id,
                           Model model) {
        return administratorService.findById(id)
                .map(administrator -> {
                    model.addAttribute("administrator", administrator);
                    model.addAttribute("roles", Role.values());
                    return "administrator/administrator";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("administrator") AdministratorCreateUpdateDto createDto) {
        model.addAttribute("administrator", createDto);
        model.addAttribute("roles", Role.values());
        return "administrator/create";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated AdministratorCreateUpdateDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()){
            try {
                administratorService.create(dto);
                return "redirect:/administrators";
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.administrator.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("administrator", dto);
        return "redirect:/administrators/create";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated AdministratorCreateUpdateDto updateAdministrator,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("administrator", updateAdministrator);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/administrators/" + id;
        }

        return administratorService.update(id, updateAdministrator)
                .map(it -> "redirect:/administrators/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!administratorService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/administrators";
    }
}
