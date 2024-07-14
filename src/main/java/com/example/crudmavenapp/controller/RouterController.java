package com.example.crudmavenapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouterController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("update")
    public String update() {
        return "update.html";
    }


}
