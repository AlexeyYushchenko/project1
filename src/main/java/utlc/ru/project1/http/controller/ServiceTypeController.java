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
import utlc.ru.project1.dto.servicetype.ServiceTypeCreateUpdateDto;
import utlc.ru.project1.service.ServiceTypeService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/serviceTypes")
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    @GetMapping
    public String findAll(Model model) {
        var serviceTypes = serviceTypeService.findAll();
        model.addAttribute("serviceTypes", serviceTypes);
        return "serviceType/serviceTypes";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id,
                           Model model) {
        return serviceTypeService.findById(id)
                .map(serviceType -> {
                    model.addAttribute("serviceType", serviceType);
                    return "serviceType/serviceType";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("serviceType") ServiceTypeCreateUpdateDto createDto) {
        model.addAttribute("serviceType", createDto);
        return "serviceType/create";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated ServiceTypeCreateUpdateDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()){
            try {
                serviceTypeService.create(dto);
                return "redirect:/serviceTypes";
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.serviceType.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("serviceType", dto);
        return "redirect:/serviceTypes/create";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated ServiceTypeCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()) {
            try {
                return serviceTypeService.update(id, createUpdateDto)
                        .map(it -> "redirect:/serviceTypes/{id}")
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.serviceType.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("serviceType", createUpdateDto);
        return "redirect:/serviceTypes/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!serviceTypeService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/serviceTypes";
    }
}