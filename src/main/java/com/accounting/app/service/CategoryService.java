package com.accounting.app.service;

import com.accounting.app.dto.CategoryRequest;
import com.accounting.app.dto.CategoryResponse;
import com.accounting.app.dto.mapper.CategoryMapper;
import com.accounting.app.exeption.ResourceNotFoundExeption;
import com.accounting.app.models.Category;
import com.accounting.app.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UserService userService;


    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, UserService userService) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.userService = userService;

    }
    public Category getCategoryByIdEntity(long id) {
        return categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundExeption("دسته بندی یافت نشد."));
    }
    public CategoryResponse getCategoryById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::toRespose).orElseThrow(()->new ResourceNotFoundExeption("دسته بندی یافت نشد."));

    }

    public CategoryResponse addCategory(CategoryRequest categoryRequest, Long userId) {
        Category category= categoryMapper.toEntity(categoryRequest,userService.getUserByIdEntity(userId));
        return categoryMapper.toRespose(categoryRepository.save(category));
    }
    public List<CategoryResponse> getAllCategoryOfUser(long userId) {
        return categoryRepository.findCategoriesByUser(userService.getUserByIdEntity(userId)).stream().map(categoryMapper::toRespose).collect(Collectors.toList());
    }
    public CategoryResponse updateCategory(CategoryRequest categoryRequest, Long userId, Long categoryId) {
        Category category= categoryMapper.toEntity(categoryRequest,userService.getUserByIdEntity(userId));
        category.setId(categoryId);
        return categoryMapper.toRespose(categoryRepository.save(category));
    }
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
