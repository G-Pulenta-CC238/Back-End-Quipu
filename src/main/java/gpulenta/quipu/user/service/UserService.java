package gpulenta.quipu.user.service;

import gpulenta.quipu.user.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    void deleteById(Long id);

    User update(User user);
}
