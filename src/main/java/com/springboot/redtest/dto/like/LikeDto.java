package com.springboot.redtest.dto.like;

import com.springboot.redtest.dto.quote.QuoteDto;
import com.springboot.redtest.dto.user.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LikeDto {

    private String publicUserId;
    private String publicQuoteId;

    private long likes;
//    private boolean isLiked;

    private UserDto user;
    private QuoteDto quote;
}
