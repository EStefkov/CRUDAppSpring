package com.example.crudmavenapp.controller;

import com.example.crudmavenapp.entities.User;
import com.example.crudmavenapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Optional;
import io.swagger.annotations.*;

@Controller
@RequestMapping("/users")
@Api(value = "User Management System", description = "Operations pertaining to user in User Management System")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "View a list of available users", response = List.class)
    @GetMapping("")
    public String getAllUsers(Model model,
                              @RequestParam(name = "searchTerm", required = false) String searchTerm,
                              @RequestParam(name = "sort", defaultValue = "firstName") String sort) {
        List<User> users;

        if (searchTerm != null && !searchTerm.isEmpty()) {
            users = userRepository.findByFirstNameContainingIgnoreCase(searchTerm, Sort.by(sort).ascending());
        } else {
            users = userRepository.findAll(Sort.by(sort).ascending());
        }

        model.addAttribute("users", users);
        model.addAttribute("searchTerm", searchTerm); // Пренасочете searchTerm обратно във формата
        return "user-list";
    }

    @ApiOperation(value = "View a list of available users", response = List.class)
    @GetMapping("/")
    public String getAllUsers(Model model, @RequestParam(defaultValue = "firstName") String sort) {
        List<User> users = userRepository.findAll(Sort.by(sort).ascending());
        model.addAttribute("users", users);
        return "user-list";
    }

    @ApiOperation(value = "Get a user by Id", response = User.class)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "Create a new user")
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createUser(User user, RedirectAttributes redirectAttributes) {
        User createdUser = userRepository.save(user);
        redirectAttributes.addFlashAttribute("message", "User created successfully!");
        return "redirect:/"; // Редиректва към основната страница
    }

    @ApiOperation(value = "Update an existing user")
    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, User userDetails, RedirectAttributes redirectAttributes) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setFirstName(userDetails.getFirstName());
            existingUser.setBirthDate(userDetails.getBirthDate());
            existingUser.setNumber(userDetails.getNumber());
            existingUser.setLastName(userDetails.getLastName());
            existingUser.setMailAddress(userDetails.getMailAddress());
            userRepository.save(existingUser);
            redirectAttributes.addFlashAttribute("message", "User updated successfully!");
            return "redirect:/users";
        } else {
            redirectAttributes.addFlashAttribute("error", "User not found!");
            return "redirect:/users";
        }
    }

    @ApiOperation(value = "Edit user by Id")
    @GetMapping("/{id}/edit")
    public String getUserByIdForUpdate(@PathVariable int id, Model model) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            return "update"; // Връщаме името на шаблона за update.html
        } else {
            // Ако не намерим потребител с даденото ID, може да върнем грешка или друга страница
            return "redirect:/users"; // Редиректваме към списъка с потребители
        }
    }

    @ApiOperation(value = "Delete a user by Id")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id, RedirectAttributes redirectAttributes) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "User not found!");
        }
        return "redirect:/users";
    }
}
