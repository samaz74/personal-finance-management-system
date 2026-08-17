package com.accounting.app.models;

import com.accounting.app.models.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @CreationTimestamp
    private LocalDateTime createdDate;

    public User(String firstName, String lastName, String nationalCode, String email, String password,Roles role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.email = email;
        this.password = password;
        this.role=role;
    }


}
