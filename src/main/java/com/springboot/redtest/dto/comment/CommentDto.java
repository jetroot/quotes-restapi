package com.springboot.redtest.dto.comment;

import com.springboot.redtest.dto.quote.QuoteDto;
import com.springboot.redtest.dto.user.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommentDto {
    private long id;
    private String publicUserId;
    private String publicQuoteId;
    private String publicCommentId;
    private String comment;
    private UserDto user;
    private QuoteDto quote;
}
