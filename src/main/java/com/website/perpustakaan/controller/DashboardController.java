package com.website.perpustakaan.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return "redirect:/auth/login";
        }

        String role = auth.getAuthorities().stream()
                        .findFirst()
                        .map(g -> g.getAuthority())
                        .orElse("");

        model.addAttribute("username", auth.getName());

        return role.equals("ROLE_ADMIN") ? "admin/dashboard" : "member/dashboard";
    }
}