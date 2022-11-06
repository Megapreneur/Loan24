package com.example.loan24.data.model;

import com.example.loan24.data.model.enumClass.Gender;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
@MappedSuperclass
public class User {
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private String address;
    @Column(unique = true)
    private String phoneNumber;
    @Email
    @Column(unique = true)
    @Valid
    private String email;
    private String password;
}
