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
import utlc.ru.project1.dto.ClientStatusDto;
import utlc.ru.project1.service.ClientStatusService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clientStatuses")
public class ClientStatusController {

    private final ClientStatusService clientStatusService;

    @GetMapping
    public String findAll(Model model, ModelAndView modelAndView){
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

    @PostMapping
    public String create(@ModelAttribute @Validated ClientStatusDto clientStatusDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("author", author);
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
//            return "redirect:/authors";
//        }
        clientStatusService.create(clientStatusDto);
        return "redirect:/clientStatuses";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated ClientStatusDto dto) {
        return clientStatusService.update(id, dto)
                .map(it -> "redirect:/clientStatuses/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!clientStatusService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/clientStatuses";
    }

}
