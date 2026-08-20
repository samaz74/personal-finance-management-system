package com.accounting.app.dto;


import com.accounting.app.models.enums.TypeOfCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryRespose {
    private Long id;
    private String name;
    private String code;
    private TypeOfCategory typeOfCategory;
    private Long userId;
    private String userName;
    private LocalDateTime createdAt;
}
