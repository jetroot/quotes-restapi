package com.springboot.redtest.dto.favorite;

import com.springboot.redtest.dto.quote.QuoteDto;
import com.springboot.redtest.dto.user.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FavoriteDto {

    private String publicUserId;
    private String publicQuoteId;

    private UserDto user;
    private QuoteDto quote;
}

