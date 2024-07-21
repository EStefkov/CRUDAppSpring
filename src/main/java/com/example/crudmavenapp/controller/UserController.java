package com.example.crudmavenapp.controller;

import com.example.crudmavenapp.entities.User;
import com.example.crudmavenapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String getAllUsers(Model model,
                              @RequestParam(name = "searchTerm", required = false) String searchTerm,
                              @RequestParam(name = "sort", defaultValue = "firstName") String sort,
                              @RequestParam(name = "order", defaultValue = "asc") String order,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sort));
        Page<User> userPage = userService.getAllUsers(searchTerm, pageable);
        model.addAttribute("userPage", userPage);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("sort", sort);
        model.addAttribute("order", order);
        return "user-list";
    }


    @GetMapping("/new")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("errorMessage", "Please correct the errors in the form.");
            return "index";
        }
        userService.createUser(user);
        redirectAttributes.addFlashAttribute("message", "User created successfully!");
        return "redirect:/users";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @Valid @ModelAttribute("user") User userDetails, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", userDetails);
            model.addAttribute("errorMessage", "Please correct the errors in the form.");
            return "update";
        }
        Optional<User> updatedUser = userService.updateUser(id, userDetails);
        if (updatedUser.isPresent()) {
            redirectAttributes.addFlashAttribute("message", "User updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "User not found!");
        }
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String getUserByIdForUpdate(@PathVariable int id, Model model) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            return "update";
        } else {
            return "redirect:/users";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id, RedirectAttributes redirectAttributes) {
        if (userService.deleteUser(id)) {
            redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "User not found!");
        }
        return "redirect:/users";
    }
}
