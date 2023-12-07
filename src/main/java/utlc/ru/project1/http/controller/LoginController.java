package utlc.ru.project1.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "administrator/login";
    }

////        DISABLED IT AS SPRING SECURITY PROVIDES 'POST' MAPPING
//    @PostMapping("/login")
//    public String login(Model model, @ModelAttribute("login") LoginDto loginDto) {
//        return "redirect:/login";
//    }
}