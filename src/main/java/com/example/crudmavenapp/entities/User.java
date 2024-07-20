
package com.example.crudmavenapp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    @Column(name="first_name")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    @Column(name="last_name")
    private String lastName;

    @NotBlank(message = "Number is mandatory")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number")
    @Column(name="number")
    private String number;

    @NotBlank(message = "Email address is mandatory")
    @Email(message = "Email should be valid")
    @Column(name="mail_address")
    private String mailAddress;

    @Past(message = "Birth date must be in the past")
    @Column(name="birth_date")
    private LocalDate birthDate;

   }
