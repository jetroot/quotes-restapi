package com.springboot.redtest.dto.quote;

import com.springboot.redtest.dto.category.CategoryDto;
import com.springboot.redtest.dto.comment.CommentDto;
import com.springboot.redtest.dto.dislike.DislikeDto;
import com.springboot.redtest.dto.like.LikeDto;
import com.springboot.redtest.dto.user.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class QuoteDto {
    private long id;
    private String publicQuoteId;
    private String quote;
    private String quoteSource;
    private String categoryName;
    private UserDto user;
    private CategoryDto category;
//    private List<CommentDto> comments;
    private List<LikeDto> likes;
    private List<DislikeDto> dislikes;
}
