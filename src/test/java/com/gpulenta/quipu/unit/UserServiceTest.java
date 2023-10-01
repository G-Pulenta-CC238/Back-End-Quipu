package com.gpulenta.quipu.unit;

import com.gpulenta.quipu.users.model.User;
import com.gpulenta.quipu.users.repository.UserRepository;
import com.gpulenta.quipu.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


public class UserServiceTest {
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }
    @Test
    void createUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.createUser(user);
        assertEquals(user, savedUser);
    }
    @Test
    void getUserByUsername() {
        String username = "testUser";
        User user = new User();
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userService.getUserByUsername(username);

        assertTrue(retrievedUser.isPresent());
        assertEquals(username, retrievedUser.get().getUsername());
    }
}
