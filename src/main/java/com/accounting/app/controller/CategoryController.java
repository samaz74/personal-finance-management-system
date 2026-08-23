package com.accounting.app.controller;

import com.accounting.app.dto.CategoryRequest;
import com.accounting.app.dto.CategoryResponse;
import com.accounting.app.service.CategoryService;
import com.accounting.app.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final UserService userService;

    public CategoryController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }



    @GetMapping("/user")
    public List<CategoryResponse> getUserCategory(Principal principal) {
        return categoryService.getAllCategoryOfUser(
                userService.getUserByEmailEntity(principal.getName()).getId());
    }
    @GetMapping("/{id}")
    public CategoryResponse getUserById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }
    @PostMapping("/")
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest, Principal principal){
        return categoryService.addCategory(categoryRequest, userService.getUserByEmailEntity(principal.getName()).getId());
    }
    @PutMapping("/{id}")
    public CategoryResponse updateCategory(@RequestBody CategoryRequest categoryRequest , Principal principal , @PathVariable Long id){
        return categoryService.updateCategory(categoryRequest,userService.getUserByEmailEntity(principal.getName()).getId(),id);
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }


}
