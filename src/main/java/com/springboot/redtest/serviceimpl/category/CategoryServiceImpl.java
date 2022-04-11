package com.springboot.redtest.serviceimpl.category;

import com.springboot.redtest.dto.category.CategoryDto;
import com.springboot.redtest.entity.category.CategoryEntity;
import com.springboot.redtest.repository.category.CategoryRepository;
import com.springboot.redtest.service.category.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ModelMapper modelMapper;

    // Send only 10 categories when user search for a category
    int categoriesPerSearch = 10;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Search for a categories based on category name
     * @param categoryName
     * @return
     */
    @Override
    public List<CategoryDto> searchCategories(String categoryName) {
        // Create a list of categories dto
        List<CategoryDto> categoriesDto = new ArrayList<>();

        // if category is not empty then get data from db
        if (!categoryName.equals("")) {
            // Set the limit of categories
            Pageable pageable = PageRequest.of(0, categoriesPerSearch);

            // Search for a category
            List<CategoryEntity> categoryEntity = categoryRepository.findAllByCategoryName(pageable, categoryName);

            // Loop through categories
            for (CategoryEntity category : categoryEntity) {

                // Transfer data from categoryEntity to categoryDto
                CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);

                // add category to categoriesDto
                categoriesDto.add(categoryDto);
            }
        }

        return categoriesDto;

    }
}
