package com.accounting.app.dto;

import com.accounting.app.models.enums.Roles;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "نام نمی توند خالی باشد.")
    private String firstName;
    @NotBlank(message = "نام خانوادگی نمی توند خالی باشد.")
    private String lastName;
    @NotBlank(message = "کد ملی نمی تواند خالی باشد.")
    private String nationalCode;
    @NotBlank(message = "ایمیل نمی تواند خالی باشد.")
    private String email;
    @NotBlank(message = "پسورد الزامی هست.")
    private String password;
    private Roles role;
}
