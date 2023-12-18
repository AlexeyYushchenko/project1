//package utlc.ru.project1.http.handler;
//
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@Slf4j
//@ControllerAdvice(basePackages = "utlc.ru.project1.http.controller")
//public class ControllerExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    private String handleExceptions(Exception e, HttpServletRequest httpServletRequest) {
//        log.error("Failed to return response: ", e);
//        return "error/error500";
//    }
//}