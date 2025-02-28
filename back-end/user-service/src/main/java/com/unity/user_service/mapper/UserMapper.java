 package com.unity.user_service.mapper;

// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
// import org.mapstruct.Named;
// import com.unity.user_service.constants.Status;
// import com.unity.user_service.dto.UserDTO;
// import com.unity.user_service.entity.User;

// public UserMapper {

//   //  @Mapping(target = "password", source = "password") // Prevent exposing passwords in DTO
//     UserDTO toDTO(User user);

//     //@Mapping(target = "id", ignore = true) // ID should not be set manually
//     @Mapping(target = "firstName", source = "firstName") // Convert Enum properly
//     User toEntity(UserDTO userDTO);

// }

import com.unity.user_service.dto.UserDTO;
import com.unity.user_service.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setStatus(user.getStatus());

        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); // Ensure password is hashed before saving
        user.setStatus(userDTO.getStatus());

        return user;
    }
}


