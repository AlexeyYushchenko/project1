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
import utlc.ru.project1.dto.agent.AgentCreateUpdateDto;
import utlc.ru.project1.service.AgentService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/agents")
public class AgentController {

    private final AgentService agentService;

    @GetMapping
    public String findAll(Model model, ModelAndView modelAndView) {
        var agents = agentService.findAll();
        model.addAttribute("agents", agents);
        return "agent/agents";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id,
                           Model model) {
        return agentService.findById(id)
                .map(agent -> {
                    model.addAttribute("agent", agent);
                    return "agent/agent";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model, @ModelAttribute("agent") AgentCreateUpdateDto createDto) {
        model.addAttribute("agent", createDto);
        model.addAttribute("roles", Role.values());
        return "agent/create";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated AgentCreateUpdateDto dto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()){
            try {
                agentService.create(dto);
                return "redirect:/agents";
            } catch (DataIntegrityViolationException e) {
                bindingResult.reject("database error", "error.database.agent.uniqueConstraintViolation");
            }
        }
        redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        redirectAttributes.addFlashAttribute("agent", dto);
        return "redirect:/agents/create";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated AgentCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("agent", createUpdateDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/agents/" + id;
        }

        return agentService.update(id, createUpdateDto)
                .map(it -> "redirect:/agents/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!agentService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/agents";
    }
}

