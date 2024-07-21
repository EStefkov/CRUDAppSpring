package com.example.crudmavenapp.service;

import com.example.crudmavenapp.entities.User;
import com.example.crudmavenapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(String searchTerm, String sort) {
        Sort sortOrder = Sort.by(sort).ascending();
        return userRepository.findBySearchTerm(searchTerm, sortOrder);
    }

    public List<User> getAllUsers(String searchTerm, String sort, String order) {
        Sort sortOrder = Sort.by(Sort.Direction.fromString(order), sort);
        return userRepository.findBySearchTerm(searchTerm, sortOrder);
    }

    public Page<User> getAllUsers(String searchTerm, Pageable pageable) {
        return userRepository.findBySearchTerm(searchTerm, pageable);
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(int id, User userDetails) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setFirstName(userDetails.getFirstName());
            existingUser.setBirthDate(userDetails.getBirthDate());
            existingUser.setNumber(userDetails.getNumber());
            existingUser.setLastName(userDetails.getLastName());
            existingUser.setMailAddress(userDetails.getMailAddress());
            return Optional.of(userRepository.save(existingUser));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
