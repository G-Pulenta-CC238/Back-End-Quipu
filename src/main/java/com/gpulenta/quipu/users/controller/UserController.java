package com.gpulenta.quipu.users.controller;

import com.gpulenta.quipu.users.model.User;
import com.gpulenta.quipu.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Operation(summary = "Create a new user", description = "Create a new user with the provided information")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    @Operation(summary = "Get a user by ID", description = "Get a user's details by their ID")
    @ApiResponse(responseCode = "200", description = "User details retrieved successfully")
    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Get a user by username", description = "Get a user's details by their username")
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Get users by email", description = "Get a list of users by their email address")
    @GetMapping("/byEmail")
    public ResponseEntity<List<User>> getUsersByEmail(@RequestParam String email) {
        List<User> users = userService.getUsersByEmail(email);
        return ResponseEntity.ok(users);
    }
    @Operation(summary = "Get users by last name", description = "Get a list of users by their last name")
    @GetMapping("/byLastName")
    public ResponseEntity<List<User>> getUsersByLastName(@RequestParam String lastName) {
        List<User> users = userService.getUsersByLastname(lastName);
        return ResponseEntity.ok(users);
    }
    @Operation(summary = "Update user by ID", description = "Update an existing user's information by their ID")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        updatedUser.setId(id);
        User updatedUserData = userService.updateUser(updatedUser);
        if (updatedUserData != null) {
            return ResponseEntity.ok(updatedUserData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Delete user by ID", description = "Delete a user by their ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
