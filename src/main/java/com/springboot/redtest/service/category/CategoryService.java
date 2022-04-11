package com.springboot.redtest.service.category;

import com.springboot.redtest.dto.category.CategoryDto;

import java.util.List;

public interface CategoryService {
    /**
     * Search for a category by category name
     * @param categoryName
     * @return
     */
    List<CategoryDto> searchCategories(String categoryName);
}
