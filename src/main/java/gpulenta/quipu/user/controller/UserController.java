package gpulenta.quipu.user.controller;

import gpulenta.quipu.user.model.User;
import gpulenta.quipu.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Find all users
    @Operation(summary = "Get all users", description = "Get all users details")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        if (userService.findAll().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    // Find user by id
    @Operation(summary = "Get user by id", description = "Get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Delete user by id
    @Operation(summary = "Delete user by id", description = "Delete user by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update user
    @Operation(summary = "Update user", description = "Update user")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (userService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        user.setId(id);
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }
}
