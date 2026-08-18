package com.accounting.app.repasitory;

import com.accounting.app.models.Category;
import com.accounting.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findCategoriesByUser(User user);
}
