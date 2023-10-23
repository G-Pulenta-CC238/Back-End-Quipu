package gpulenta.quipu.user.service.impl;

import gpulenta.quipu.user.model.User;
import gpulenta.quipu.user.repository.UserRepository;
import gpulenta.quipu.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        return existingUser.orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setAddress(user.getAddress());
            userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setPhone(user.getPhone());
            return userRepository.save(userToUpdate);
        } else {
            return null;
        }
    }
}
