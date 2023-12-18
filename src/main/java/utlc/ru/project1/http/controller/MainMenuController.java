package utlc.ru.project1.http.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainMenuController {

    @GetMapping("/menu")
    public String showMainMenu() {
        return "menu";
    }
}
