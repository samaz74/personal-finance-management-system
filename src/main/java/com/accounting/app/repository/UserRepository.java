package com.accounting.app.repository;

import com.accounting.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findUsersByFirstNameContaining(String firstName);

    List<User> findUsersByLastNameContaining(String lastName);

    Optional<User> findUserByEmail(String email);

    List<User> findUsersByEmailContaining(String email);

    List<User> getUserByLastNameContaining(String lastName);

    boolean existsByEmail(String email);
}
