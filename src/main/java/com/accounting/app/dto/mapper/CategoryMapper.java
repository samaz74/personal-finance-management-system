package com.accounting.app.dto.mapper;

import com.accounting.app.dto.CategoryRequest;
import com.accounting.app.dto.CategoryRespose;
import com.accounting.app.models.Category;
import com.accounting.app.models.User;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryRequest categoryRequest, User user) {
        return new Category(
                categoryRequest.getName(),
                categoryRequest.getCode(),
                categoryRequest.getTypeOfCategory(),
                user
        );
    }
    public CategoryRespose toRespose(Category category) {
        return new CategoryRespose(
                category.getId(),
                category.getName(),
                category.getCode(),
                category.getTypeOfCategory(),
                category.getUser().getId(),
                category.getUser().getFirstName().concat(" " + category.getUser().getLastName()),
                category.getCreatedAt()
        );
    }
}
