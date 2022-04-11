package com.springboot.redtest.dto.dislike;

import com.springboot.redtest.dto.quote.QuoteDto;
import com.springboot.redtest.dto.user.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DislikeDto {

    private String publicUserId;
    private String publicQuoteId;

    private long dislikes;

    private UserDto user;
    private QuoteDto quote;
}
