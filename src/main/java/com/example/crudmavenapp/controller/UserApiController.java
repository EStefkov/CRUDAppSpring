package com.example.crudmavenapp.controller;

import com.example.crudmavenapp.entities.User;
import com.example.crudmavenapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;

import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(
            @RequestParam(value = "searchTerm", defaultValue = "") String searchTerm,
            @RequestParam(value = "sort", defaultValue = "id") String sort) {
        return userService.getAllUsers(searchTerm, sort);
    }

    @GetMapping("/paged")
    public Page<User> getPagedUsers(
            @RequestParam(value = "searchTerm", defaultValue = "") String searchTerm,
            Pageable pageable) {
        return userService.getAllUsers(searchTerm, pageable);
    }


    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public Optional<User> updateUser(@PathVariable int id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }


}
