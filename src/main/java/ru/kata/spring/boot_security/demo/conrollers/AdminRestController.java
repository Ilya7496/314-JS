//package ru.kata.spring.boot_security.demo.conrollers;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.demo.models.Role;
//import ru.kata.spring.boot_security.demo.models.User;
//import ru.kata.spring.boot_security.demo.service.UserService;
//import ru.kata.spring.boot_security.demo.service.RoleService;
//import ru.kata.spring.boot_security.demo.util.PersonValidator;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminRestController {
//
//    private final PersonValidator personValidator;
//    private final RoleService roleService;
//    private final UserService userService;
//
//    @Autowired
//    public AdminRestController(PersonValidator personValidator, RoleService roleService, UserService userService) {
//        this.personValidator = personValidator;
//        this.roleService = roleService;
//        this.userService = userService;
//    }
//
//
//    @GetMapping("/users")
//    public String showAllUsers(@AuthenticationPrincipal User user, Model model) {
//        model.addAttribute("user", user);
//        model.addAttribute("users", userService.getAllUsers());
//        model.addAttribute("person", new User());
//        List<Role> roles = roleService.findAll();
//        model.addAttribute("allRoles", roles);
//        return "users";
//    }
//
//    @PostMapping("/addNewUser")
//    public String addNewUser(@ModelAttribute("person") @Valid User user, BindingResult bindingResult) {
//        personValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "users";
//        }
//        userService.addNewUser(user);
//        return "redirect:/admin/users";
//    }
//
//    @PostMapping("/edit")
//    public String editUser(@ModelAttribute("showUser") @Valid User user) {
//        userService.edit(user);
//        return "redirect:/admin/users";
//    }
//
//
//    @PostMapping("/delete")
//    public String deleteUserId(@ModelAttribute("showUser") User user) {
//        userService.deleteUser(user.getId());
//        return "redirect:/admin/users";
//    }
//
//}
package ru.kata.spring.boot_security.demo.conrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.dto.AdminPageResponse;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.util.PersonValidator;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private final PersonValidator personValidator;
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public AdminRestController(PersonValidator personValidator, RoleService roleService, UserService userService) {
        this.personValidator = personValidator;
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/users")
    public AdminPageResponse showAllUsers(@AuthenticationPrincipal User user) {
        List<User> users = userService.getAllUsers();
        List<Role> roles = roleService.findAll();

        AdminPageResponse response = new AdminPageResponse();
        response.setUser(user);
        response.setUsers(users);
        response.setAllRoles(roles);
        response.setPerson(new User());

        return response;
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody @Valid User user) {
        userService.addNewUser(user);
        return "User added successfully";
    }

    @PostMapping("/edit")
    public String editUser(@RequestBody @Valid User user) {
        userService.edit(user);
        return "User updated successfully";
    }

    @PostMapping("/delete")
    public String deleteUserId(@RequestBody User user) {
        userService.deleteUser(user.getId());
        return "User deleted successfully";
    }
}