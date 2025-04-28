package com.website.perpustakaan.controller;

import com.website.perpustakaan.model.User;
import com.website.perpustakaan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // gunakan interface PasswordEncoder, bukan langsung BCrypt

    // Halaman manajemen user
    @GetMapping("/users")
    public String showUserManagement(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/manajemen-user";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("username", "Admin"); // Ganti kalau nanti mau dinamis
        return "admin/dashboard";
    }

    // Tambah user baru
    @PostMapping("/users/add")
    public String addUser(@RequestParam String fullName,
                          @RequestParam String username,
                          @RequestParam String email,
                          @RequestParam String password,
                          @RequestParam String role) {

        User user = new User();
        user.setFullName(fullName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        // Ubah string role jadi enum (ADMIN atau MEMBER)
        user.setRole(User.Role.valueOf(role.toUpperCase()));

        userRepository.save(user);
        return "redirect:/admin/users";
    }

    // Hapus user
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }
}
