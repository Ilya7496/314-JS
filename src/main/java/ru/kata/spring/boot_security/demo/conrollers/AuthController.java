package ru.kata.spring.boot_security.demo.conrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AuthController {

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("auth/login");
    }
}
