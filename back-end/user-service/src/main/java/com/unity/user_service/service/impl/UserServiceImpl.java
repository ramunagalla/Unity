package com.unity.user_service.service.impl;

import com.unity.user_service.dto.UserDTO;
import com.unity.user_service.entity.User;
import com.unity.user_service.repository.UserRepository;
import com.unity.user_service.service.UserService;
import com.unity.user_service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO registerUser(UserDTO userDTO) {
        // Check if email or username already exists
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already in use!");
        }
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username already taken!");
        }

        // Convert DTO to Entity
        User user = UserMapper.toEntity(userDTO);

        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // Save to database
        User savedUser = userRepository.save(user);

        // Convert back to DTO and return
        return UserMapper.toDTO(savedUser);
    }
}
