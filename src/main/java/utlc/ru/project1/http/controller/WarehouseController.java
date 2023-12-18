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
import utlc.ru.project1.dto.warehouse.WarehouseCreateUpdateDto;
import utlc.ru.project1.service.CountryService;
import utlc.ru.project1.service.WarehouseService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;
    private final CountryService countryService;

    @GetMapping
    public String findAll(Model model, ModelAndView modelAndView) {
        var warehouses = warehouseService.findAll();
        model.addAttribute("warehouses", warehouses);
        return "warehouse/warehouses";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id,
                           Model model) {
        return warehouseService.findById(id)
                .map(warehouse -> {
                    model.addAttribute("warehouse", warehouse);
                    model.addAttribute("countries", countryService.findAll());
                    return "warehouse/warehouse";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("warehouse") WarehouseCreateUpdateDto createDto) {
        model.addAttribute("warehouse", createDto);
        model.addAttribute("roles", Role.values());
        return "warehouse/create";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated WarehouseCreateUpdateDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()){
            try {
                warehouseService.create(dto);
                return "redirect:/warehouses";
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.warehouse.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("warehouse", dto);
        return "redirect:/warehouses/create";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated WarehouseCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("warehouse", createUpdateDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/warehouses/" + id;
        }

        return warehouseService.update(id, createUpdateDto)
                .map(it -> "redirect:/warehouses/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!warehouseService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/warehouses";
    }
}
