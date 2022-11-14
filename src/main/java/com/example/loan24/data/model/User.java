package com.example.loan24.data.model;

import com.example.loan24.data.model.enumClass.Gender;
import com.example.loan24.data.model.enumClass.Authority;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;
@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<Authority> authority = new HashSet<>();
}
