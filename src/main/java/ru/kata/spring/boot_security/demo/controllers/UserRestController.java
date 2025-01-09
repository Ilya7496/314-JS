//package ru.kata.spring.boot_security.demo.controllers;
//
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import ru.kata.spring.boot_security.demo.models.User;
//
//@Controller
//public class UserController {
//
//    @RequestMapping("/user")
//    public String user(@AuthenticationPrincipal User user, Model model) {
//        model.addAttribute("user", user);
//        return "user";
//    }
//}
package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.User;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @GetMapping("/user")
    public User user(@AuthenticationPrincipal User user) {
        if (user == null) {
            throw new RuntimeException("User is not authenticated");
        }
        return user;
    }
}
