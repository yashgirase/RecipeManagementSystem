package com.example.Entity;

import com.example.Entity.Enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Pattern(regexp = "^\\w+@gmail\\.com$")
    private String email;

    @NotBlank
    private String password;

    @Pattern(regexp = "^\\d{10}$")
    private String mobileNumber;


}
