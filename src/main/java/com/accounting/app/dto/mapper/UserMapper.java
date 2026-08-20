package com.accounting.app.dto.mapper;

import com.accounting.app.dto.UserRequest;
import com.accounting.app.dto.UserResponse;
import com.accounting.app.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequest userRequest) {
        return new User(
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getNationalCode(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getRole()
        );
    }
    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getNationalCode(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedDate()
        );
    }
}
