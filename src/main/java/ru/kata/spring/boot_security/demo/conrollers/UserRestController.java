//package ru.kata.spring.boot_security.demo.conrollers;
//
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ru.kata.spring.boot_security.demo.models.User;
//
//@RestController
//public class UserController {
//
//    @RequestMapping("/user")
//    public String user(@AuthenticationPrincipal User user, Model model) {
//        model.addAttribute("user", user);
//        return "user";
//    }
//}

//package ru.kata.spring.boot_security.demo.controllers;
package ru.kata.spring.boot_security.demo.conrollers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.models.User;

@RestController
public class UserRestController {

    @RequestMapping("/user")
    public ModelAndView user(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}

