package com.example.loan24.dto.request;

import com.example.loan24.data.model.enumClass.Gender;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAdminRequest {
    private String name;
    private Gender gender;
    private String address;
    private String password;
    private String email;
    private String confirmPassword;
}
