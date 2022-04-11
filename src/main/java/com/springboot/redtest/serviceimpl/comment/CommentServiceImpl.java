package com.springboot.redtest.serviceimpl.comment;

import com.springboot.redtest.common.Utility;
import com.springboot.redtest.dto.comment.CommentDto;
import com.springboot.redtest.dto.quote.QuoteDto;
import com.springboot.redtest.dto.user.UserDto;
import com.springboot.redtest.entity.comment.CommentEntity;
import com.springboot.redtest.entity.quote.QuoteEntity;
import com.springboot.redtest.entity.user.UserEntity;
import com.springboot.redtest.exception.UserException;
import com.springboot.redtest.repository.comment.CommentRepository;
import com.springboot.redtest.repository.quote.QuoteRepository;
import com.springboot.redtest.repository.user.UserRepository;
import com.springboot.redtest.service.comment.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private CommentRepository commentRepository;

    private Utility utility;

    @Autowired
    private ModelMapper modelMapper;

    // Comments should be shown
    int commentPerPage = 2;

    /**
     * Create new comment
     * @param commentDto
     */
    @Override
    public void createComment(CommentDto commentDto) {
        // Search for user
        UserEntity userEntity = userRepository.findByPublicUserId(commentDto.getPublicUserId());

        // if user does not exist
        if (userEntity == null) throw new UserException("User does not exists");

        // Search for a quote
        QuoteEntity quoteEntity = quoteRepository.findByPublicQuoteId(commentDto.getPublicQuoteId());

        // if quote does not exist
        if (quoteEntity == null) throw new UserException("Quote does not exists");

        // Transfer data to UserDto
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);

        // Transfer data to QuoteDto
        QuoteDto quoteDto = modelMapper.map(quoteEntity, QuoteDto.class);

        // Set user for comment
        commentDto.setUser(userDto);

        // Set quote for comment
        commentDto.setQuote(quoteDto);

        // Set comment publicCommentId
        commentDto.setPublicCommentId(utility.UUID16());

        // Transfer data to CommentEntity
        CommentEntity commentEntity = modelMapper.map(commentDto, CommentEntity.class);

        // Save data to database
        commentRepository.save(commentEntity);

    }

    /**
     * Update an existing comment
     * @param publicCommentId
     * @param commentDto
     */
    @Override
    public void updateComment(String publicCommentId, CommentDto commentDto) {
        // Check if comment exists
        CommentEntity commentEntity = commentRepository.findByPublicCommentId(publicCommentId);

        // If comment does not exist
        if (commentEntity == null) throw new UserException("Comment does not exists");

        // update comment
        commentEntity.setComment(commentDto.getComment());

        // Save data to database
        commentRepository.save(commentEntity);
    }

    /**
     * Delete a comment
     * @param publicCommentId
     * @return
     */
    @Override
    public void deleteComment(String publicCommentId) {
        // Search for a comment
        CommentEntity commentEntity = commentRepository.findByPublicCommentId(publicCommentId);

        // if comment does not exist
        if (commentEntity == null) throw new UserException("Comment does not exists");

        // remove comment from database
        commentRepository.delete(commentEntity);
    }

    /**
     * Get all comments for a quote
     * @param page
     * @return
     */
    @Override
    public List<CommentDto> getAllComments(String publicQuoteId, String page) {
        // Search for a quote
        QuoteEntity quoteEntity = quoteRepository.findByPublicQuoteId(publicQuoteId);

        // if quote does not exist
        if (quoteEntity == null) throw new UserException("Quote not found");

        // Convert page string to integer
        Integer pageInt = Integer.valueOf(page);

        // Get rid of 0
        if (pageInt > 0) pageInt -= 1;

        // Define which page and the limit of quotes
        // that will be shown in that page
        Pageable pageable = PageRequest.of(pageInt, commentPerPage);

        // Find all comments that defined in the pageable AKA (page, limit)
        Page<CommentEntity> commentsPage = commentRepository.findAllByQuote(pageable, quoteEntity);

        // Get the content of comments in that page
        List<CommentEntity> comments = commentsPage.getContent();

        // Store list of quotesDto
        List<CommentDto> listCommentsDto = new ArrayList<>();

        // Loop through comments
        for (CommentEntity commentEntity :  comments) {
            // Transfer data to commentDto
            CommentDto commentDto = modelMapper.map(commentEntity, CommentDto.class);

            // Add each comment to the listCommentsDto
            listCommentsDto.add(commentDto);
        }

        return listCommentsDto;

    }

}
