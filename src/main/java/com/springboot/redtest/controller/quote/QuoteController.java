package com.springboot.redtest.controller.quote;

import com.springboot.redtest.dto.dislike.DislikeDto;
import com.springboot.redtest.dto.like.LikeDto;
import com.springboot.redtest.dto.quote.QuoteDto;
import com.springboot.redtest.request.common.CommonRequest;
import com.springboot.redtest.request.quote.QuoteRequest;
import com.springboot.redtest.response.category.CategoryResponse;
import com.springboot.redtest.response.quote.QuoteResponse;
import com.springboot.redtest.service.quote.QuoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/quotes")
public class QuoteController {
    @Autowired
    private QuoteService quoteService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get all quotes
     * @param page
     * @return
     */
    @GetMapping
    public List<QuoteResponse> getAllQuotes(@RequestParam(value = "page", defaultValue = "1") String page) {

        // List of quotes
        List<QuoteResponse> responseListQuotes = new ArrayList<QuoteResponse>();

        // Quotes in QuoteDto
        List<QuoteDto> quotes = quoteService.getAllQuotes(page);

        // List of likes
        List<LikeDto> likes = new ArrayList<>();

        // List of dislikes
        List<DislikeDto> dislikes = new ArrayList<>();

        // Loop through quotes
        for (QuoteDto quoteDto : quotes) {

            // Transfer quoteDto to responseListQuotes
            QuoteResponse quote = modelMapper.map(quoteDto, QuoteResponse.class);
            quote.setUserImage(quoteDto.getUser().getImage());
            quote.setAuthorId(quoteDto.getUser().getPublicUserId());
            quote.setCategoryName(quote.getCategory().getCategoryName());
            likes = quoteDto.getLikes();
            dislikes = quoteDto.getDislikes();

            quote.setLike(likes.size());
            quote.setDislike(dislikes.size());

            // Add each quote to responseListQuotes
            responseListQuotes.add(quote);
        }

        // return responseList to the user
        return responseListQuotes;
    }

    /**
     * Get a single quote
     * @param publicQuoteId
     * @return
     */
    @GetMapping(value = "/{publicQuoteId}")
    public ResponseEntity<QuoteResponse> getQuote(@PathVariable("publicQuoteId") String publicQuoteId) {

        // Get quote
        QuoteDto quoteDto = quoteService.getQuote(publicQuoteId);

        // Transfer data to quoteResponse
        QuoteResponse quoteResponse = modelMapper.map(quoteDto, QuoteResponse.class);

        // Set quoteResponse info
        quoteResponse.setUserImage(quoteDto.getUser().getImage());
        quoteResponse.setLike(quoteDto.getLikes().size());
        quoteResponse.setDislike(quoteDto.getDislikes().size());

        // Store all comments from quoteDto
//        List<CommentDto> comments = quoteDto.getComments();


//        List<CommentResponse> commentsResponse = new ArrayList<CommentResponse>();
//
//        for (CommentDto comment : comments) {
//
//            CommentResponse commentResponse = new CommentResponse();
//            commentResponse.setPublicCommentId(comment.getPublicCommentId());
//            commentResponse.setComment(comment.getComment());
//            commentResponse.setUserImage(comment.getUser().getImage());
//            commentResponse.setUserName(comment.getUser().getFirstName());
//
//            commentsResponse.add(commentResponse);
//
//            quoteResponse.setComments(commentsResponse);
//
//        }


        return new ResponseEntity<QuoteResponse>(quoteResponse, HttpStatus.OK);
    }

    /**
     * Create new quote
     * @param publicUserId
     * @param quoteRequest (quote, quoteSource)
     */
    @PostMapping(value = "/createQuote/{publicUserId}")
    public ResponseEntity<Void> createQuote(@PathVariable("publicUserId") String publicUserId, @Valid @RequestBody QuoteRequest quoteRequest) throws Exception {

        QuoteDto quoteDto = modelMapper.map(quoteRequest, QuoteDto.class);

        quoteService.createQuote(publicUserId, quoteDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Update a quote
     * @param publicQuoteId
     * @param quoteRequest
     * @return
     */
    @PutMapping(path = "/{publicQuoteId}")
    public ResponseEntity<Void> updateQuote(@PathVariable("publicQuoteId") String publicQuoteId, @Valid @RequestBody QuoteRequest quoteRequest) {

        QuoteDto quoteDto = modelMapper.map(quoteRequest, QuoteDto.class);

        quoteService.updateQuote(publicQuoteId, quoteDto);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Delete a quote
     * @param commonRequest
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteQuote(@Valid @RequestBody CommonRequest commonRequest) {

        quoteService.deleteQuote(commonRequest);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
