package com.springboot.redtest.serviceimpl.dislike;

import com.springboot.redtest.dto.dislike.DislikeDto;
import com.springboot.redtest.dto.quote.QuoteDto;
import com.springboot.redtest.dto.user.UserDto;
import com.springboot.redtest.entity.dislike.DislikeEntity;
import com.springboot.redtest.entity.like.LikeEntity;
import com.springboot.redtest.entity.quote.QuoteEntity;
import com.springboot.redtest.entity.user.UserEntity;
import com.springboot.redtest.exception.UserException;
import com.springboot.redtest.repository.dislike.DislikeRepository;
import com.springboot.redtest.repository.like.LikeRepository;
import com.springboot.redtest.repository.quote.QuoteRepository;
import com.springboot.redtest.repository.user.UserRepository;
import com.springboot.redtest.service.dislike.DislikeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DislikeServiceImpl implements DislikeService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private DislikeRepository dislikeRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Dislike a quote
     * @param dislikeDto
     */
    @Override
    public void dislike(DislikeDto dislikeDto) {
        // Search for a user
        UserEntity userEntity = userRepository.findByPublicUserId(dislikeDto.getPublicUserId());

        // if user not found
        if (userEntity == null) throw new UserException("User not found");

        // Search for a quote
        QuoteEntity quoteEntity = quoteRepository.findByPublicQuoteId(dislikeDto.getPublicQuoteId());

        // If quote not found
        if (quoteEntity == null) throw new UserException("Quote not found");

        // Search for a like made by this user
        LikeEntity likeEntity = likeRepository.findByUserAndQuote(userEntity, quoteEntity);

        // if like found then delete it
        if (likeEntity != null) likeRepository.delete(likeEntity);

        // Search for dislike made by this user
        DislikeEntity dislikeEntity = dislikeRepository.findByUserAndQuote(userEntity, quoteEntity);

        // if dislike found then delete it
        if (dislikeEntity != null) {
            dislikeRepository.delete(dislikeEntity);

            return;
        }

        // Transfer data to userDto
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);

        // Set user for dislike
        dislikeDto.setUser(userDto);

        // Set dislikes for this quote
        dislikeDto.setDislikes(1);

        // Transfer data to quoteDto
        QuoteDto quoteDto = modelMapper.map(quoteEntity, QuoteDto.class);

        // Set quote for dislike
        dislikeDto.setQuote(quoteDto);

        // Transfer data to DislikeEntity
        DislikeEntity dislikeEntity1 = modelMapper.map(dislikeDto, DislikeEntity.class);

        // if no dislike made by this user before then do dislike
        dislikeRepository.save(dislikeEntity1);
    }
}
