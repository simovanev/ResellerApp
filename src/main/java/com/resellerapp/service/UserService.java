package com.resellerapp.service;

import com.resellerapp.model.dtos.LoginDto;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isLogged(LoginDto loginDto) {
        Optional<User> user = userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
        return user.isPresent();
    }
}
