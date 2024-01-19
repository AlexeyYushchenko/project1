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
import utlc.ru.project1.dto.clientstatus.ClientStatusCreateUpdateDto;
import utlc.ru.project1.service.ClientStatusService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/clientStatuses")
public class ClientStatusController {

    private final ClientStatusService clientStatusService;

    @GetMapping
    public String findAll(Model model){
        var clientStatuses = clientStatusService.findAll();
        model.addAttribute("clientStatuses", clientStatuses);
        return "clientStatus/clientStatuses";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id,
                           Model model) {
         return clientStatusService.findById(id)
                .map(clientStatus -> {
                    model.addAttribute("clientStatus", clientStatus);
                    return "clientStatus/clientStatus";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("clientStatus") ClientStatusCreateUpdateDto createDto) {
        model.addAttribute("clientStatus", createDto);
        return "clientStatus/create";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated ClientStatusCreateUpdateDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()){
            try {
                clientStatusService.create(dto);
                return "redirect:/clientStatuses";
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.clientStatus.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("clientStatus", dto);
        return "redirect:/clientStatuses/create";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated ClientStatusCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()) {
            try{
                return clientStatusService.update(id, createUpdateDto)
                        .map(it -> "redirect:/clientStatuses/{id}")
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            }catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.clientStatus.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("clientStatus", createUpdateDto);
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        return "redirect:/clientStatuses/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!clientStatusService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/clientStatuses";
    }

}
