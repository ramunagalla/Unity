package com.unity.user_service.controller;

import com.unity.user_service.dto.LoginRequestDTO;
import com.unity.user_service.dto.UserDTO;
import com.unity.user_service.exception.UserException;
import com.unity.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.registerUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/getUserById")
    public ResponseEntity<UserDTO> getUserById(@RequestParam Long id) {
        Optional<UserDTO> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDTO> updateUser(@RequestParam Long id, @RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUser = userService.updateUser(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/softDeleteUser")
    public ResponseEntity<?> softDeleteUser(@RequestParam Long id) {
        userService.softDeleteUser(id);
        return ResponseEntity.ok("User deleted successfully!");
    }

    @PutMapping("/restoreUser")
    public ResponseEntity<?> restoreUser(@RequestParam Long id) {
        userService.restoreUser(id);
        return ResponseEntity.ok("User restored successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            UserDTO user = userService.loginUser(loginRequest); // Fetch user details
            return ResponseEntity.ok(user); // Return user details instead of a string
        } catch (UserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/approveUser")
    public ResponseEntity<String> approveUser(@RequestParam Long userId, @RequestParam Long adminId) {
        userService.approveUser(userId, adminId);
        return ResponseEntity.ok("User approved successfully!");
    }

    @PutMapping("/deactivateUser")
    public ResponseEntity<String> deactivateUser(@RequestParam Long userId, @RequestParam Long adminId) {
        userService.deactivateUser(userId, adminId);
        return ResponseEntity.ok("User login deactivated!");
    }

}
