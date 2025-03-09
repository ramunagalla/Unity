package com.unity.user_service.service;

import com.unity.user_service.dto.UserDTO;
import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    Optional<UserDTO> getUserById(Long id);
    Optional<UserDTO> getUserByEmail(String email);
    Optional<UserDTO> getUserByUsername(String username);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserDTO userDTO);
    void softDeleteUser(Long id);
    void restoreUser(Long id);
}
