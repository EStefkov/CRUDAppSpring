package com.example.crudmavenapp.repository;

import com.example.crudmavenapp.entities.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

   List<User> findByFirstNameContainingIgnoreCase(String searchTerm, Sort ascending);
}
