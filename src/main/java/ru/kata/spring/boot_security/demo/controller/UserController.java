package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String printAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping("/admin")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model, Role roles) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.getAllUsers());
            return "admin";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/page-delete")
    public String pageForDelete(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.showUserById(id));
        return "delete";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/page-edit")
    public String pageForEdit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.showUserById(id));
        return "edit";
    }

    @PostMapping("/admin/edit")
    public String editUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        userService.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String printUser(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userService.showUserById(user.getId()));
        return "user";
    }

    @GetMapping("/index")
    public String printIndex() {
        return "index";
    }
}
