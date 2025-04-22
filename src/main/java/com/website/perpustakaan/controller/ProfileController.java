package com.website.perpustakaan.controller;

import com.website.perpustakaan.model.Profile;
import com.website.perpustakaan.model.User;
import com.website.perpustakaan.repository.ProfileRepository;
import com.website.perpustakaan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping
    public String viewProfile(Model model, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        // Check if user has profile, if not create new one
        Optional<Profile> optionalProfile = profileRepository.findByUser(user);
        Profile profile = optionalProfile.orElse(new Profile());

        // Years 2018 - 2025
        List<Integer> tahunList = new ArrayList<>();
        for (int i = 2025; i >= 2018; i--) {
            tahunList.add(i);
        }

        model.addAttribute("user", user);
        model.addAttribute("profile", profile);
        model.addAttribute("tahunList", tahunList);
        return "member/profile";
    }

    @PostMapping
    public String updateProfile(@ModelAttribute Profile profile, 
                              BindingResult bindingResult,
                              Principal principal, 
                              Model model) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        // Validate tahun angkatan
        if (profile.getTahunAngkatan() < 2018 || profile.getTahunAngkatan() > 2025) {
            bindingResult.rejectValue("tahunAngkatan", "error.profile", "Tahun angkatan harus antara 2018 dan 2025.");
        }

        // Validate jenis kelamin
        if (profile.getJenisKelamin() == null || profile.getJenisKelamin().isEmpty()) {
            bindingResult.rejectValue("jenisKelamin", "error.profile", "Jenis kelamin harus dipilih.");
        }

        if (bindingResult.hasErrors()) {
            List<Integer> tahunList = new ArrayList<>();
            for (int i = 2025; i >= 2018; i--) {
                tahunList.add(i);
            }

            model.addAttribute("user", user);
            model.addAttribute("tahunList", tahunList);
            return "member/profile";
        }

        profile.setUser(user);
        profileRepository.save(profile);
        return "redirect:/dashboard";
    }
}