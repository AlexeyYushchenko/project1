package utlc.ru.project1.http.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.security.auth.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
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