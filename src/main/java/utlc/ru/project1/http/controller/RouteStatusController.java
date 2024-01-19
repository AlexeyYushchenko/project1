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
import utlc.ru.project1.dto.routestatus.RouteStatusCreateUpdateDto;
import utlc.ru.project1.service.RouteStatusService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/routeStatuses")
public class RouteStatusController {

    private final RouteStatusService routeStatusService;

    @GetMapping
    public String findAll(Model model) {
        var routeStatuses = routeStatusService.findAll();
        model.addAttribute("routeStatuses", routeStatuses);
        return "routeStatus/routeStatuses";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id,
                           Model model) {
        return routeStatusService.findById(id)
                .map(routeStatus -> {
                    model.addAttribute("routeStatus", routeStatus);
                    return "routeStatus/routeStatus";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("routeStatus") RouteStatusCreateUpdateDto createDto) {
        model.addAttribute("routeStatus", createDto);
        return "routeStatus/create";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated RouteStatusCreateUpdateDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()){
            try {
                routeStatusService.create(dto);
                return "redirect:/routeStatuses";
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.routeStatus.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("routeStatus", dto);
        return "redirect:/routeStatuses/create";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated RouteStatusCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()) {
            try {
                return routeStatusService.update(id, createUpdateDto)
                        .map(it -> "redirect:/routeStatuses/{id}")
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.routeStatus.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("routeStatus", createUpdateDto);
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        return "redirect:/routeStatuses/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!routeStatusService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/routeStatuses";
    }

}