package com.accounting.app.service;

import com.accounting.app.dto.UserRequest;
import com.accounting.app.dto.UserResponse;
import com.accounting.app.dto.mapper.UserMapper;
import com.accounting.app.exeption.ResourceNotFoundExeption;
import com.accounting.app.models.User;
import com.accounting.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    public User getUserByIdEntity(long id) {
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundExeption("کاربر یافت نشد."));
    }
    public UserResponse getUserById(long id) {
        return userRepository.findById(id).map(userMapper::toUserResponse).orElseThrow(()->new ResourceNotFoundExeption("کاربر یافت نشد."));
    }

    public List<UserResponse> getUserByFirstName(String firstName) {
        return userRepository.findUsersByFirstNameContaining(firstName).stream().map(userMapper::toUserResponse).collect(Collectors.toList());
    }
    public List<UserResponse> getUserByEmail(String email) {
        return userRepository.findUsersByEmailContaining(email).stream().map(userMapper::toUserResponse).collect(Collectors.toList());
    }
    public List<UserResponse> getUserByLastName(String lastName) {
        return userRepository.findUsersByLastNameContaining(lastName).stream().map(userMapper::toUserResponse).collect(Collectors.toList());
    }
    public User getUserByEmailEntity(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundExeption("کاربر یافت نشد."));
    }

    public UserResponse addUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        return userMapper.toUserResponse( userRepository.save(user));
    }
    public UserResponse updateUser(Long id,UserRequest userRequest) {
        if (userRepository.existsById(id)){
            User user = userMapper.toEntity(userRequest);
            user.setId(id);
            return userMapper.toUserResponse( userRepository.save(user));
        }else throw new ResourceNotFoundExeption("کاربر یافت نشد.");
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }

}
