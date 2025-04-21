package com.website.perpustakaan.controller;

import com.website.perpustakaan.dto.UserRegistrationDto;
import com.website.perpustakaan.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm(
        @RequestParam(value = "error", required = false) String error,
        @RequestParam(value = "logout", required = false) String logout,
        @RequestParam(value = "registered", required = false) String registered,
        Model model
    ) {
        if (error != null) {
            model.addAttribute("error", "Username atau password salah");
        }
        if (logout != null) {
            model.addAttribute("message", "Anda telah logout");
        }
        if (registered != null) {
            model.addAttribute("success", "Registrasi berhasil! Silakan login");
        }
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginUser() {
        // Proses login sebenarnya ditangani oleh Spring Security
        return "redirect:/";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUserAccount(
        @ModelAttribute("user") UserRegistrationDto registrationDto,
        RedirectAttributes redirectAttributes
    ) {
        try {
            userService.registerNewUser(registrationDto);
            return "redirect:/auth/login?registered=true";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("user", registrationDto);
            return "redirect:/auth/register";
        }
    }

    @GetMapping("/logout")
    public String logoutUser() {
        // Proses logout ditangani oleh Spring Security
        return "redirect:/auth/login?logout=true";
    }
}