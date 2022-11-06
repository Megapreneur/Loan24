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
    private Gender gender;
    private String occupation;
    private String address;
    private String phoneNumber;
    private String nin;
    private String email;
    private String dob;
    private String accountNumber;
    private String bankName;
    private String password;
    private String confirmPassword;

}
