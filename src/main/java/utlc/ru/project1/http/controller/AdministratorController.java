package utlc.ru.project1.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import utlc.ru.project1.database.entity.Role;
import utlc.ru.project1.dto.administrator.AdministratorCreateDto;
import utlc.ru.project1.dto.administrator.AdministratorUpdateDto;
import utlc.ru.project1.service.AdministratorService;

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
                    return "administrator/administrator";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("administrator") AdministratorCreateDto createDto) {
        model.addAttribute("administrator", createDto);
        model.addAttribute("roles", Role.values());
        return "administrator/registration";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated AdministratorCreateDto administratorCreateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("administrator", administratorCreateDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/administrators/registration";
        }
        administratorService.create(administratorCreateDto);
        return "redirect:/administrators";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated AdministratorUpdateDto updateAdministrator,
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
