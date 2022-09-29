package com.example.loan24.dto.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindUserRequest {
    private String email;
}
