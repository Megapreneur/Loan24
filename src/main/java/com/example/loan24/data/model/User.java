package com.example.loan24.data.model;

import com.example.loan24.data.model.enumClass.Gender;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Valid
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private String occupation;
    private String address;
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String nin;
    @Email
    @Column(unique = true)
    @Valid
    private String email;
//    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate dob;
    @Column(unique = true)
    private String accountNumber;
    private String bankName;
    private String password;
    private String confirmPassword;
    private String guarantorName;
    @Column(unique = true)
    private String guarantorPhoneNumber;





}
