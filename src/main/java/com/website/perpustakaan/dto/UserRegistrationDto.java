package com.website.perpustakaan.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class UserRegistrationDto {
    @NotBlank(message = "NIM/NIP tidak boleh kosong")
    private String username;

    @NotBlank(message = "Nama lengkap tidak boleh kosong")
    private String fullName;

    @NotBlank(message = "Email tidak boleh kosong")
    @Email(message = "Email harus valid")
    private String email;

    @NotBlank(message = "Password tidak boleh kosong")
    @Size(min = 6, message = "Password minimal 6 karakter")
    private String password;

    @NotBlank(message = "Konfirmasi password tidak boleh kosong")
    private String confirmPassword;

    @NotBlank(message = "Role harus dipilih")
    private String role;
}