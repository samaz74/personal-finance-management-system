package com.accounting.app.Service;

import com.accounting.app.exeption.ResourceNotFoundExeption;
import com.accounting.app.models.User;
import com.accounting.app.repasitory.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundExeption("کاربر یافت نشد."));
    }
}
