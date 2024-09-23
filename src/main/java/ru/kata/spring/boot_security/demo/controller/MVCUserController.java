package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MVCUserController {

    @GetMapping("/admin/mvc")
    public String forUsers(Model model) {
        return "users";
    }

    @GetMapping("/user/mvc")
    public String forUser() {
        return "user";
    }
}
