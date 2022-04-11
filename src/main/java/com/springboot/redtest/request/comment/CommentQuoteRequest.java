package com.springboot.redtest.request.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
public class CommentQuoteRequest {
    @NotBlank(message = "Quote Id cannot be null")
    private String publicQuoteId;

    @NotBlank(message = "Page cannot be empty")
    private String page;
}
