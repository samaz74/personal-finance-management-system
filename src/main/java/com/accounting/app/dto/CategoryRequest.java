package com.accounting.app.dto;

import com.accounting.app.models.enums.TypeOfCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class CategoryRequest {
    private String name;
    private String code;
    private TypeOfCategory typeOfCategory;
}
