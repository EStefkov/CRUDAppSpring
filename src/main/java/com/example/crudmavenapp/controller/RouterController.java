package com.example.crudmavenapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.crudmavenapp.entities.User;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("/update")
    public String update(Model model) {
        model.addAttribute("user", new User());  // Може да се промени, за да се зареди съществуващ потребител при нужда
        return "update";
    }
}
