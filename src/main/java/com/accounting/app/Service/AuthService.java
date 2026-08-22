package com.accounting.app.service;

import com.accounting.app.dto.UserRequest;
import com.accounting.app.dto.mapper.AuthResponse;
import com.accounting.app.dto.mapper.UserMapper;
import com.accounting.app.exeption.AccessDeniedExeption;
import com.accounting.app.exeption.DuplicateResourceExeption;
import com.accounting.app.models.InvalidatedToken;
import com.accounting.app.models.User;
import com.accounting.app.models.enums.Roles;
import com.accounting.app.repasitory.InvalidatedTokenRepository;
import com.accounting.app.repasitory.UserRepository;
import com.accounting.app.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final InvalidatedTokenRepository invalidatedTokenRepository;
    private final UserMapper userMapper;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil, InvalidatedTokenRepository invalidatedTokenRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.invalidatedTokenRepository = invalidatedTokenRepository;
        this.userMapper = userMapper;
    }

    public AuthResponse registerUser(UserRequest userRequest){
        if(userRepository.existsByEmail(userRequest.getEmail())){
            throw new DuplicateResourceExeption("ایمیل تکراری است.");
        }else{
            User user = userMapper.toEntity(userRequest);
            user.setRole(Roles.USER);
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            userRepository.save(user);
            return new AuthResponse(null, user.getEmail(), user.getRole());
        }
    }

    public AuthResponse login(String email, String password){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        User user = userRepository.findUserByEmail(email).orElseThrow(()-> new AccessDeniedExeption("ایمیل یافت نشد."));
        String token = jwtUtil.generateToken(user);
        return new AuthResponse (token, user.getEmail(), user.getRole());

    }

    public void logout(String token){
        String email = jwtUtil.extractEmail(token);
        LocalDateTime expireAt = jwtUtil.extractExpiration(token);
        InvalidatedToken invalidatedToken = new InvalidatedToken(token, email, expireAt);
        invalidatedTokenRepository.save(invalidatedToken);
    }
}
