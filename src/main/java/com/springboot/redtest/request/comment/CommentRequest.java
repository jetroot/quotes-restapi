package com.springboot.redtest.request.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
public class CommentRequest {
    @NotBlank(message = "Comment cannot be empty")
    private String comment;

    @NotBlank(message = "User Id cannot be null")
    private String publicUserId;

    @NotBlank(message = "Quote Id cannot be null")
    private String publicQuoteId;

}
