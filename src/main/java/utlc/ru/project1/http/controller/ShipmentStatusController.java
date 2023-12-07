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
import utlc.ru.project1.dto.shipmentstatus.ShipmentStatusCreateUpdateDto;
import utlc.ru.project1.service.ShipmentStatusService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shipmentStatuses")
public class ShipmentStatusController {

    private final ShipmentStatusService shipmentStatusService;

    @GetMapping
    public String findAll(Model model, ModelAndView modelAndView) {
        var shipmentStatuses = shipmentStatusService.findAll();
        model.addAttribute("shipmentStatuses", shipmentStatuses);
        return "shipmentStatus/shipmentStatuses";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id,
                           Model model) {
        return shipmentStatusService.findById(id)
                .map(shipmentStatus -> {
                    model.addAttribute("shipmentStatus", shipmentStatus);
                    return "shipmentStatus/shipmentStatus";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute @Validated ShipmentStatusCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shipmentStatus", createUpdateDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/shipmentStatuses";
        }
        shipmentStatusService.create(createUpdateDto);
        return "redirect:/shipmentStatuses";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated ShipmentStatusCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shipmentStatus", createUpdateDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/shipmentStatuses/" + id;
        }

        return shipmentStatusService.update(id, createUpdateDto)
                .map(it -> "redirect:/shipmentStatuses/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!shipmentStatusService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/shipmentStatuses";
    }

}
