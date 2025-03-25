package com.example.apiauthcrud.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING)  // Format sebagai string
    private BigDecimal price;
}
