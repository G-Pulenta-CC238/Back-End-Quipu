package com.gpulenta.quipu.users.repository;

import com.gpulenta.quipu.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    List<User> findByEmail(String email);
    List<User> findByLastname(String lastName);
}
