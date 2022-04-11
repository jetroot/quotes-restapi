package com.springboot.redtest.controller.comment;

import com.springboot.redtest.dto.comment.CommentDto;
import com.springboot.redtest.request.comment.CommentRequest;
import com.springboot.redtest.request.comment.UpdateCommentRequest;
import com.springboot.redtest.response.comment.CommentResponse;
import com.springboot.redtest.service.comment.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper modelMapper;


    /**
     * Get all comment with pagination
     * @param publicQuoteId : id of quote
     * @param page : page number for pagination
     * @return list of comments
     */
    @GetMapping
    public List<CommentResponse> getAllComments(@NotBlank(message = "Quote Id cannot be null") @RequestParam(name = "qid") String publicQuoteId,
                                                @RequestParam(name = "p", defaultValue = "1") String page) {

        // List of comments
        List<CommentResponse> responseListComments = new ArrayList<CommentResponse>();

        // Comments in CommentDto
        List<CommentDto> comments = commentService.getAllComments(publicQuoteId, page);

        // Loop through quotes
        for (CommentDto commentDto : comments) {

            // Transfer CommentDto to responseListComments
            CommentResponse commentResponse = modelMapper.map(commentDto, CommentResponse.class);
            commentResponse.setUserImage(commentDto.getUser().getImage());
            commentResponse.setUserName(String.format("%s %s", commentDto.getUser().getFirstName(), commentDto.getUser().getLastName()));

            // Add each quote to responseListQuotes
            responseListComments.add(commentResponse);
        }

        // return comments to the user
        return responseListComments;
    }

    /**
     * Create new comment
     * @param commentRequest
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/createComment")
    public ResponseEntity<Void> createComment(@Valid @RequestBody CommentRequest commentRequest) {

        CommentDto commentDto = modelMapper.map(commentRequest, CommentDto.class);

        commentService.createComment(commentDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Update an existing comment
     * @param publicCommentId
     * @param updateCommentRequest
     * @return
     */
    @PutMapping(path = "/{publicCommentId}")
    public ResponseEntity<Void> updateComment(@PathVariable("publicCommentId") String publicCommentId,
                                           @Valid @RequestBody UpdateCommentRequest updateCommentRequest) {

        CommentDto commentDto = modelMapper.map(updateCommentRequest, CommentDto.class);

        commentService.updateComment(publicCommentId, commentDto);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Delete a comment
     * @param publicCommentId
     * @return
     */
    @DeleteMapping(path = "/{publicCommentId}")
    public ResponseEntity<Object> deleteComment(@PathVariable("publicCommentId") String publicCommentId) {

        commentService.deleteComment(publicCommentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
