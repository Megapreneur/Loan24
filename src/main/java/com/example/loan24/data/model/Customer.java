package com.example.loan24.data.model;

import com.example.loan24.data.model.enumClass.Gender;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Valid
public class Customer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String occupation;
    @Column(unique = true)
    private String nin;
    private LocalDate dob;
    @Column(unique = true)
    private String accountNumber;
    private String bankName;






}
