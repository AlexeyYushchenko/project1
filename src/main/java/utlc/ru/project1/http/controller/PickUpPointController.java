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
import utlc.ru.project1.dto.pickuppoint.PickUpPointCreateUpdateDto;
import utlc.ru.project1.service.CountryService;
import utlc.ru.project1.service.PickUpPointService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pickUpPoints")
public class PickUpPointController {

    private final PickUpPointService pickUpPointService;
    private final CountryService countryService;

    @GetMapping
    public String findAll(Model model, ModelAndView modelAndView) {
        var pickUpPoints = pickUpPointService.findAll();
        model.addAttribute("pickUpPoints", pickUpPoints);
        return "pickUpPoint/pickUpPoints";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id,
                           Model model) {
        return pickUpPointService.findById(id)
                .map(pickUpPoint -> {
                    model.addAttribute("pickUpPoint", pickUpPoint);
                    model.addAttribute("countries", countryService.findAll());
                    return "pickUpPoint/pickUpPoint";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute @Validated PickUpPointCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("pickUpPoint", createUpdateDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/pickUpPoints";
        }
        pickUpPointService.create(createUpdateDto);
        return "redirect:/pickUpPoints";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute @Validated PickUpPointCreateUpdateDto createUpdateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("pickUpPoint", createUpdateDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/pickUpPoints/" + id;
        }

        return pickUpPointService.update(id, createUpdateDto)
                .map(it -> "redirect:/pickUpPoints/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!pickUpPointService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/pickUpPoints";
    }
}
