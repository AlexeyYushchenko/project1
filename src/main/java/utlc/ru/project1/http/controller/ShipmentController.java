package utlc.ru.project1.http.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import utlc.ru.project1.dto.shipment.ShipmentCreateUpdateDto;
import utlc.ru.project1.service.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;
    private final ShipmentStatusService shipmentStatusService;
    private final CountryService countryService;
    private final ClientService clientService;
    private final PriorityService priorityService;
    private final RouteService routeService;
    private final PickUpPointService pickUpPointService;
    private final ManufacturerService manufacturerService;
    
    @GetMapping
    public String findAll(Model model) {
        var shipments = shipmentService.findAll();
        model.addAttribute("shipments", shipments);
        return "shipment/shipments";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           Model model) {
        return shipmentService.findById(id)
                .map(shipment -> {
                    model.addAttribute("shipment", shipment);
                    addAllAttributesToModel(model);

                    return "shipment/shipment";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("shipment") ShipmentCreateUpdateDto createDto) {
        model.addAttribute("shipment", createDto);
        addAllAttributesToModel(model);
        return "shipment/create";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated ShipmentCreateUpdateDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()) {
            try {
                shipmentService.create(dto);
                return "redirect:/shipments";
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.shipment.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("shipment", dto);
        return "redirect:/shipments/create";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute @Validated ShipmentCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()) {
            try {
                return shipmentService.update(id, createUpdateDto)
                        .map(it -> "redirect:/shipments/{id}")
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.shipment.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("shipment", createUpdateDto);
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        return "redirect:/shipments/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!shipmentService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/shipments";
    }
    private void addAllAttributesToModel(Model model) {
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("shipmentStatuses", shipmentStatusService.findAll());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("priorities", priorityService.findAll());
        model.addAttribute("routes", routeService.findAll());
        model.addAttribute("pickUpPoints", pickUpPointService.findAll());
        model.addAttribute("manufacturers", manufacturerService.findAll());
    }


    //todo Code Duplication (routeController & shipmentController)
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.isEmpty()) {
                    setValue(null); // Set to null if the string is empty
                } else {
                    try {
                        // Parse the LocalDateTime if not empty
                        LocalDateTime dateTime = LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        setValue(dateTime);
                    } catch (DateTimeParseException e) {
                        setValue(null); // Handle parse error, set null or throw IllegalArgumentException
                    }
                }
            }

            @Override
            public String getAsText() {
                Object value = getValue();
                return (value != null ? value.toString() : "");
            }
        });
    }
}
