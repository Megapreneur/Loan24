package com.example.loan24.dto.request;

import com.example.loan24.data.model.enumClass.Gender;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class RegisterUserRequest {
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
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private String dob;
    @Column(unique = true)
    private String accountNumber;
    private String bankName;
    private String password;
    private String confirmPassword;
    private String guarantorName;
    private String guarantorPhoneNumber;
}
