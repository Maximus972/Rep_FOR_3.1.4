package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/admin/")
    public String listUsers(Model model) {
        model.addAttribute("listUsers", userService.findAll());
        return "users";
    }

    @GetMapping("/user/")
    public String userList(Model model) {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("user"));
        model.addAttribute("user", new User("Ruslan", "Ruslan@yandex.ru", roles , "Rusik001", "12345678"));
        return "user";
    }

    @GetMapping("/admin/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", new Role());
//        model.addAttribute("role", new Role());
//        List<Role> roles = new ArrayList<>();
//        roles.add(new Role("USER"));
//        roles.add(new Role("ADMIN"));
        return "add-user";
    }

    @PostMapping("/admin/save")
    public String saveUser(@ModelAttribute("user") User user, @ModelAttribute("role") Role role) {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        userService.saveUserWithRole(user);
        return "redirect:/admin/";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/";
    }

    @PostMapping("/admin/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        User user = userService.findById(id);
        Role role = user.getRoles().get(0);
        model.addAttribute("user", user);
        model.addAttribute("role", role);
        return "add-user";
    }
}
