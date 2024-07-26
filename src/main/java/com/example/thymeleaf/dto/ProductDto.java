package com.example.thymeleaf.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto{
        private Long id;
        private String name;
        private BigDecimal price;
        private LocalDate registrationDate;
}