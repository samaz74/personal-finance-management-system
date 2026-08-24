package com.accounting.app.controller;

import com.accounting.app.dto.UserRequest;
import com.accounting.app.dto.UserResponse;
import com.accounting.app.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public UserResponse getUserWithId(Principal principal){
        return userService.getUserById(userService.getUserByEmailEntity(principal.getName()).getId());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse getUserWithIdAdmin(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("search/firstName/{firstName}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUserWithFirstName(@PathVariable String firstName){
        return userService.getUserByFirstName(firstName);
    }

    @GetMapping("search/lastName/{lastName}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUserWithLastName(@PathVariable String lastName){
        return userService.getUserByLastName(lastName);
    }

    @GetMapping("search/email/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUserWithEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse updateUser(@RequestBody UserRequest userRequest, @PathVariable Long id){
        return userService.updateUser(id,userRequest);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
