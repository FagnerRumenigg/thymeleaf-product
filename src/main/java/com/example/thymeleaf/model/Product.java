package com.example.thymeleaf.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id_product")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name_product")
    private String name;

    @Column(name ="price_product")
    private BigDecimal price;

    @Column(name ="date_registration_product")
    private LocalDate registrationDate;
}
