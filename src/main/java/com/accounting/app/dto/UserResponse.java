package com.accounting.app.dto;

import com.accounting.app.models.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String email;
    private Roles role;
    private LocalDateTime createdDate;
}
