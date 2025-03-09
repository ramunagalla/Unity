package com.unity.user_service.service.impl;

import com.unity.user_service.constants.Status;
import com.unity.user_service.dto.UserDTO;
import com.unity.user_service.entity.User;
import com.unity.user_service.exception.UserException;
import com.unity.user_service.mapper.UserMapper;
import com.unity.user_service.repository.UserRepository;
import com.unity.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        userDTO.setStatus(Status.PENDING);
        User user = UserMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        user = userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(userDTO.getFirstName());
                    existingUser.setLastName(userDTO.getLastName());
                    existingUser.setPhoneNumber(userDTO.getPhoneNumber());
                    existingUser.setDateOfBirth(userDTO.getDateOfBirth());
                    existingUser.setStatus(userDTO.getStatus());

                    if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                        existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                    }

                    userRepository.save(existingUser);
                    return UserMapper.toDTO(existingUser);
                })
                .orElseThrow(() -> new UserException("User not found!"));
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return Optional.ofNullable(
                userRepository.findById(id)
                        .filter(user -> !user.isDeleted())
                        .map(UserMapper::toDTO)
                        .orElseThrow(() -> new UserException("User not found with ID: " + id)));
    }

    @Override
    public Optional<UserDTO> getUserByEmail(String email) {
        return Optional.ofNullable(
                userRepository.findByEmail(email)
                        .filter(user -> !user.isDeleted())
                        .map(UserMapper::toDTO)
                        .orElseThrow(() -> new UserException("User not found with email: " + email)));
    }

    @Override
    public Optional<UserDTO> getUserByUsername(String username) {
        return Optional.ofNullable(
                userRepository.findByUsername(username)
                        .filter(user -> !user.isDeleted())
                        .map(UserMapper::toDTO)
                        .orElseThrow(() -> new UserException("User not found with username: " + username)));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAllActiveUsers()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void softDeleteUser(Long id) {
        userRepository.softDeleteUser(id);
    }

    @Override
    @Transactional
    public void restoreUser(Long id) {
        userRepository.findById(id)
                .ifPresent(user -> {
                    user.setDeleted(false);
                    userRepository.save(user);
                });
    }
}
