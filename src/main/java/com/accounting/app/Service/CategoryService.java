package com.accounting.app.Service;

import com.accounting.app.exeption.ResourceNotFoundExeption;
import com.accounting.app.models.Category;
import com.accounting.app.repasitory.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundExeption("دسته بندی یافت نشد."));
    }
}
