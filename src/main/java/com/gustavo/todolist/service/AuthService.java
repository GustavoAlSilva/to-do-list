package com.gustavo.todolist.service;

import com.gustavo.todolist.entity.User;
import com.gustavo.todolist.exception.AuthenticationException;
import com.gustavo.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {
            throw new AuthenticationException("Invalid username and/or password");
        }

        return user;
    }
}
