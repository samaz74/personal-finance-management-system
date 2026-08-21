package com.accounting.app.repasitory;

import com.accounting.app.dto.UserResponse;
import com.accounting.app.models.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findUsersByFirstNameContaining(String firstName);

    List<User> findUsersByLastNameContaining(String lastName);

    Optional<User> findUserByEmail(String email);

    List<User> findUsersByEmailContaining(String email);

    List<UserResponse> getUserByLastNameContaining(@NotBlank(message = "نام خانوادگی نمی توند خالی باشد.") String lastName);
}
