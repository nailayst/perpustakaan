package com.website.perpustakaan.service;

import com.website.perpustakaan.dto.UserRegistrationDto;
import com.website.perpustakaan.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User registerNewUser(UserRegistrationDto registrationDto);
}