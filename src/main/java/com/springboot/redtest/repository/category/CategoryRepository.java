package com.springboot.redtest.repository.category;

import com.springboot.redtest.entity.category.CategoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Long> {
    /**
     * Find a category by category name
     * @param categoryName
     * @return
     */
    CategoryEntity findByCategoryName(String categoryName);

    /**
     * Search for a category by a category name, used in searching for a category
     * @param pageableRequest
     * @param query
     * @return
     */
    @Query(value="SELECT c FROM CategoryEntity c WHERE c.categoryName LIKE %:q%")
    List<CategoryEntity> findAllByCategoryName(Pageable pageableRequest, @Param("q") String query);
}
