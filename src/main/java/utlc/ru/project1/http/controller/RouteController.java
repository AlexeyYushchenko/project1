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
import utlc.ru.project1.dto.route.RouteCreateUpdateDto;
import utlc.ru.project1.service.CountryService;
import utlc.ru.project1.service.RouteService;
import utlc.ru.project1.service.RouteStatusService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final RouteStatusService routeStatusService;
    private final CountryService countryService;

    @GetMapping
    public String findAll(Model model) {
        var routes = routeService.findAll();
        model.addAttribute("routes", routes);
        return "route/routes";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           Model model) {
        return routeService.findById(id)
                .map(route -> {
                    model.addAttribute("route", route);
                    model.addAttribute("routeStatuses", routeStatusService.findAll());
                    model.addAttribute("countries", countryService.findAll());
                    return "route/route";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("route") RouteCreateUpdateDto createDto) {
        model.addAttribute("route", createDto);
        model.addAttribute("routeStatuses", routeStatusService.findAll());
        model.addAttribute("countries", countryService.findAll());
        return "route/create";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated RouteCreateUpdateDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()){
            try {
                routeService.create(dto);
                return "redirect:/routes";
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.route.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("route", dto);
        return "redirect:/routes/create";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute @Validated RouteCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()) {
            try {
                return routeService.update(id, createUpdateDto)
                        .map(it -> "redirect:/routes/{id}")
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.route.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("route", createUpdateDto);
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        return "redirect:/routes/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!routeService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/routes";
    }
}
