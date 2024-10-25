package com.resellerapp.service;

import com.resellerapp.model.dtos.LoginDto;
import com.resellerapp.model.dtos.RegisterDto;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.session.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private CurrentUser currentUser;

    public UserService(UserRepository userRepository, PasswordEncoder encoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.currentUser = currentUser;
    }

    public boolean isRegistered(LoginDto loginDto) {
        Optional<User> user = userRepository.findByUsername(loginDto.getUsername());
        return encoder.matches(loginDto.getPassword(), user.get().getPassword());
    }

    public boolean goodCredentials(RegisterDto registerDto) {
        return registerDto.getPassword().equals(registerDto.getConfirmPassword());
    }

    public boolean isDuplicateEmail(RegisterDto registerDto) {
        return userRepository.findByEmail(registerDto.getEmail()).isPresent();
    }

    public void saveUser(RegisterDto registerDto) {
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(encoder.encode(registerDto.getPassword()));
        userRepository.save(user);
    }

    public void login(LoginDto loginDto) {
        Optional<User> user = userRepository.findByUsername(loginDto.getUsername());
        currentUser.setId(user.get().getId());
        currentUser.setName(user.get().getUsername());

    }

    public void logout() {
        currentUser.setId(0);
        currentUser.setName(null);
    }


}
