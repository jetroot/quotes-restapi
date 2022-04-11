package com.springboot.redtest.serviceimpl.like;

import com.springboot.redtest.dto.like.LikeDto;
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
import com.springboot.redtest.service.like.LikeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private DislikeRepository dislikeRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Like a quote
     * @param likeDto
     */
    @Override
    public void like(LikeDto likeDto) {
        // Search for a user
        UserEntity userEntity = userRepository.findByPublicUserId(likeDto.getPublicUserId());

        // if user not found
        if (userEntity == null) throw new UserException("User not found");

        // Search for a quote
        QuoteEntity quoteEntity = quoteRepository.findByPublicQuoteId(likeDto.getPublicQuoteId());

        // If quote not found
        if (quoteEntity == null) throw new UserException("Quote not found");

        // Search for a dislike made by this user
        DislikeEntity dislikeEntity = dislikeRepository.findByUserAndQuote(userEntity, quoteEntity);

        // if dislike found then delete it
        if (dislikeEntity != null) dislikeRepository.delete(dislikeEntity);

        // Search for like made by this user
        LikeEntity likeEntity = likeRepository.findByUserAndQuote(userEntity, quoteEntity);

        // if like found then delete it
        if (likeEntity != null) {
            likeRepository.delete(likeEntity);

            return;
        }

        // Transfer data to userDto
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);

        // Set user for like
        likeDto.setUser(userDto);

        // Set likes for this quote
        likeDto.setLikes(1);

        // Transfer data to quoteDto
        QuoteDto quoteDto = modelMapper.map(quoteEntity, QuoteDto.class);

        // Set quote for like
        likeDto.setQuote(quoteDto);

        // Transfer data to LikeEntity
        LikeEntity likeEntity1 = modelMapper.map(likeDto, LikeEntity.class);

        // if no like made by this user before
        // create like
        likeRepository.save(likeEntity1);

    }
}
