package com.gpulenta.quipu.users.service;

import com.gpulenta.quipu.users.model.User;
import com.gpulenta.quipu.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) { return userRepository.save(user); }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsersByLastname(String lastName) {
        return userRepository.findByLastname(lastName);
    }

    public Optional<User> getUserById(int id) { return userRepository.findById(id); }

    public void deleteUserById(int id) { userRepository.deleteById(id); }

    public User updateUser(User updatedUser) {
        Optional<User> existingUser = userRepository.findById(updatedUser.getId());

        if (existingUser.isPresent()) {

            User userToUpdate = existingUser.get();
            userToUpdate.setUsername(updatedUser.getUsername());
            userToUpdate.setPassword(updatedUser.getPassword());
            userToUpdate.setName(updatedUser.getName());
            userToUpdate.setLastname(updatedUser.getLastname());
            userToUpdate.setAddress(updatedUser.getAddress());
            userToUpdate.setEmail(updatedUser.getEmail());
            userToUpdate.setPhone(updatedUser.getPhone());

            return userRepository.save(userToUpdate);
        } else {
            return null;
        }
    }
}
