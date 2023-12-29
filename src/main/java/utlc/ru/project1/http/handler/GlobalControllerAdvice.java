package utlc.ru.project1.http.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Value("${app.default-language}")
    private String defaultLanguage;

    @ModelAttribute("selectedLanguage")
    public String determineSelectedLanguage(@RequestParam(value = "lang", required = false) String lang, HttpServletRequest request) {
        if (lang == null || lang.isEmpty()) {
            lang = defaultLanguage;
        }
        request.getSession().setAttribute("lang", lang);
        return lang;
    }

}
