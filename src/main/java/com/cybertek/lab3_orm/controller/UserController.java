package com.cybertek.lab3_orm.controller;

import com.cybertek.lab3_orm.model.AppUser;
import com.cybertek.lab3_orm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotNull;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "user/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("appUser", new AppUser());
        return "user/register";
    }

    @PostMapping("/register")
    public String createUser(@NotNull @ModelAttribute("user") AppUser appUser){
        userService.createUser(appUser);
        return "redirect:/login";
    }
}
