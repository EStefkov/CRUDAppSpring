package com.example.crudmavenapp;

import com.example.crudmavenapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudMavenAppApplication  {

    public static void main(String[] args) {
        SpringApplication.run(CrudMavenAppApplication.class, args);
    }

}
