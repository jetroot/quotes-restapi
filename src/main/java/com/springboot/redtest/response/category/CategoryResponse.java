package com.springboot.redtest.response.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryResponse {
    private long id;
    private String categoryName;
}
