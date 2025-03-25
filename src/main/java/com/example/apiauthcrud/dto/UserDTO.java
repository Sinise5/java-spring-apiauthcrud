package com.example.apiauthcrud.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
