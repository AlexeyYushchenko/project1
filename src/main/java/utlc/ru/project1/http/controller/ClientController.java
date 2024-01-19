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
import utlc.ru.project1.dto.client.ClientCreateUpdateDto;
import utlc.ru.project1.service.BusinessTypeService;
import utlc.ru.project1.service.ClientService;
import utlc.ru.project1.service.ClientStatusService;
import utlc.ru.project1.service.IndustryTypeService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientStatusService clientStatusService;
    private final IndustryTypeService industryTypeService;
    private final BusinessTypeService businessTypeService;

    @GetMapping
    public String findAll(Model model) {
        var clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "client/clients";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id,
                           Model model) {
        return clientService.findById(id)
                .map(client -> {
                    model.addAttribute("client", client);
                    model.addAttribute("clientStatuses", clientStatusService.findAll());
                    model.addAttribute("industryTypes", industryTypeService.findAll());
                    model.addAttribute("businessTypes", businessTypeService.findAll());
                    return "client/client";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("client") ClientCreateUpdateDto createDto) {
        model.addAttribute("client", createDto);
        model.addAttribute("clientStatuses", clientStatusService.findAll());
        model.addAttribute("industryTypes", industryTypeService.findAll());
        model.addAttribute("businessTypes", businessTypeService.findAll());
        return "client/create";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated ClientCreateUpdateDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()){
            try {
                clientService.create(dto);
                return "redirect:/clients";
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.client.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("client", dto);
        return "redirect:/clients/create";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated ClientCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()) {
            try {
                return clientService.update(id, createUpdateDto)
                        .map(it -> "redirect:/clients/{id}")
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.client.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("client", createUpdateDto);
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        return "redirect:/clients/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!clientService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/clients";
    }
}
