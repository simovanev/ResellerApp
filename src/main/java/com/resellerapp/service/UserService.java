package com.resellerapp.service;

import com.resellerapp.model.dtos.LoginDto;
import com.resellerapp.model.dtos.RegisterDto;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public boolean isLogged(LoginDto loginDto) {
        Optional<User> user = userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
        return user.isPresent();
    }

    public boolean goodCredentials(RegisterDto registerDto) {
        boolean equalsPasswords = registerDto.getPassword().equals(registerDto.getConfirmPassword());
        if (equalsPasswords) {
            User user = new User();
            user.setUsername(registerDto.getUsername());
            user.setEmail(registerDto.getEmail());
            user.setPassword(encoder.encode(registerDto.getPassword()));
            userRepository.save(user);
        }
        return equalsPasswords;
    }
}
