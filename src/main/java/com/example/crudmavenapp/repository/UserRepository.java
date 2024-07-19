package com.example.crudmavenapp.repository;

import com.example.crudmavenapp.entities.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

   @Query("SELECT u FROM User u WHERE " +
           "(:searchTerm IS NULL OR u.firstName LIKE %:searchTerm%) OR " +
           "(:searchTerm IS NULL OR u.lastName LIKE %:searchTerm%) OR " +
           "(:searchTerm IS NULL OR u.number LIKE %:searchTerm%) OR " +
           "(:searchTerm IS NULL OR u.mailAddress LIKE %:searchTerm%)")
   List<User> findBySearchTerm(@Param("searchTerm") String searchTerm, Sort sort);
}
