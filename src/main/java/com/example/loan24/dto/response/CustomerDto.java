package com.example.loan24.dto.response;

import com.example.loan24.data.model.enumClass.Gender;
import lombok.*;


import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private String name;
    private Gender gender;
    private String address;
    private String phoneNumber;
    private String email;
    private String occupation;
    private String nin;
    private LocalDate dob;
    private String accountNumber;
    private String bankName;
}
