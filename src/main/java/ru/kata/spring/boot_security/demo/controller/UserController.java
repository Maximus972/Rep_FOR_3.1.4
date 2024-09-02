package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String listUsers(Model model) {
        model.addAttribute("listUsers", userService.findAll());
        return "users";
    }

    @GetMapping("/user/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/user/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @PostMapping("/user/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/user/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "add-user";  // JSP форма редактирования
    }

}
