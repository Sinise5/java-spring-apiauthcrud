package com.example.apiauthcrud.dto;

public class ApiResponseDTO {
    private String message;
    private Object data;  // Ganti 'token' dengan 'data' untuk menyimpan objek produk atau daftar produk

    public ApiResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
