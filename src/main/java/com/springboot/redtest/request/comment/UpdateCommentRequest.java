package com.springboot.redtest.request.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
public class UpdateCommentRequest {

    @NotEmpty(message = "Comment cannot be empty")
    private String comment;

}
