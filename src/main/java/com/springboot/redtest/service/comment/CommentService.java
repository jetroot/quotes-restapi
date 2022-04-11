package com.springboot.redtest.service.comment;

import com.springboot.redtest.dto.comment.CommentDto;

import java.util.List;

public interface CommentService {
    /**
     * Create new comment
     * @param commentDto
     */
    void createComment(CommentDto commentDto);

    /**
     * Update an existing comment
     * @param publicCommentId
     * @param commentDto
     */
    void updateComment(String publicCommentId, CommentDto commentDto);

    /**
     * Delete a comment
     * @param publicCommentId
     * @return
     */
    void deleteComment(String publicCommentId);

    /**
     * Get all comments for a quote
     * @param page
     * @return
     */
    List<CommentDto> getAllComments(String publicQuoteId, String page);

}
