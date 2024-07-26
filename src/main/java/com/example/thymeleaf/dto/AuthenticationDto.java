package com.example.thymeleaf.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticationDto{
        private String email;

        private String password;
}