package com.example.crudmavenapp.service;

import com.example.crudmavenapp.entities.User;
import com.example.crudmavenapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//docker run --name mysql-db -e MYSQL_ROOT_PASSWORD=rootpassword -e
// MYSQL_DATABASE=mydatabase -e MYSQL_USER=myuser -e MYSQL_PASSWORD=mypassword
// -p 3306:3306 -d mysql:latest
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(String searchTerm, String sort) {
        Sort sortOrder = Sort.by(sort).ascending();
        return userRepository.findBySearchTerm(searchTerm, sortOrder);
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
