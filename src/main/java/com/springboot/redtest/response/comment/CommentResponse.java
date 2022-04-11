package com.springboot.redtest.response.comment;

import com.springboot.redtest.response.user.UserResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommentResponse {
//    private UserResponse user;
    private String publicCommentId;
    private String userImage;
    private String userName;
    private String comment;
}
