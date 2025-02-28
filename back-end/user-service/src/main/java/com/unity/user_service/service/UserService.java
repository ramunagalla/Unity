package com.unity.user_service.service;

import com.unity.user_service.dto.UserDTO;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
}
