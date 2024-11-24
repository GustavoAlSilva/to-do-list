package com.gustavo.todolist.service;

import com.gustavo.todolist.dto.UserResponseDTO;
import com.gustavo.todolist.entity.User;
import com.gustavo.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(Integer id, User updatedUser) {
        User existingUser = findById(id);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setEmail(updatedUser.getEmail());
        return userRepository.save(existingUser);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
