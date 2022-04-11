package com.springboot.redtest.response.quote;

import com.springboot.redtest.response.category.CategoryResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class QuoteResponse {
    private String publicQuoteId;
    private String quote;
    private String quoteSource;
    private String userImage;
    private String authorId;
    private String categoryName;
    private CategoryResponse category;
    private long like;
    private long dislike;
}
