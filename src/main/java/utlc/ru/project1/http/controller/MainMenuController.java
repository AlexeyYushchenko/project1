package utlc.ru.project1.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainMenuController {

    @GetMapping("/menu")
    public String showMainMenu() {
        return "menu";
    }
}
