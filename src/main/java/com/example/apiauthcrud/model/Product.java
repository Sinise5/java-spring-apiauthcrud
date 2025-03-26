package com.example.apiauthcrud.model;

import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)  // Batasi panjang maksimum
    private String name;

    @Column(length = 255)  // Hindari teks terlalu panjang
    private String description;

    @Column(nullable = false, precision = 10, scale = 2) // Menyimpan angka dengan 2 desimal
    private BigDecimal price;
}
