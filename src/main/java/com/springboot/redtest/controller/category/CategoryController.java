package com.springboot.redtest.controller.category;

import com.springboot.redtest.dto.category.CategoryDto;
import com.springboot.redtest.repository.category.CategoryRepository;
import com.springboot.redtest.response.category.CategoryResponse;
import com.springboot.redtest.service.category.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<CategoryResponse> searchCategory(@RequestParam(name = "q", defaultValue = "") String categoryName) {

        // Get data from db
        List<CategoryDto> categoriesDto = categoryService.searchCategories(categoryName);

        // List of response categories
        List<CategoryResponse> categoryResponses = new ArrayList<>();

        // Loop through categoriesDto
        for (CategoryDto categoryDto : categoriesDto) {

            // Transfer data from categoriesDto to categoryResponse
            CategoryResponse categoryResponse = modelMapper.map(categoryDto, CategoryResponse.class);

            // Add each categoryDto to categoryResponses
            categoryResponses.add(categoryResponse);
        }

        return categoryResponses;
    }
}
