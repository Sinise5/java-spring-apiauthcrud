package com.example.apiauthcrud.service;

import com.example.apiauthcrud.dto.UserDTO;
import com.example.apiauthcrud.dto.AuthResponseDTO;
import com.example.apiauthcrud.model.User;
import com.example.apiauthcrud.repository.UserRepository;
import com.example.apiauthcrud.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<AuthResponseDTO> register(UserDTO userDTO) {
        if (userDTO.getUsername() == null || userDTO.getUsername().isBlank()) {
            return ResponseEntity.badRequest().body(new AuthResponseDTO("Username tidak boleh kosong!", null));
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().isBlank()) {
            return ResponseEntity.badRequest().body(new AuthResponseDTO("Password tidak boleh kosong!", null));
        }
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(new AuthResponseDTO("Username sudah terdaftar!", null));
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole("USER");

        userRepository.save(user);
        return ResponseEntity.ok(new AuthResponseDTO("User berhasil didaftarkan!", null));
    }

    public ResponseEntity<AuthResponseDTO> login(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findByUsername(userDTO.getUsername());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(401).body(new AuthResponseDTO("User tidak ditemukan!", null));
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body(new AuthResponseDTO("Password salah!", null));
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponseDTO("Login berhasil!", token));
    }
}
