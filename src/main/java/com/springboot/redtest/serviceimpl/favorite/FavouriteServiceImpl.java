package com.springboot.redtest.serviceimpl.favorite;

import com.springboot.redtest.dto.favorite.FavoriteDto;
import com.springboot.redtest.dto.quote.QuoteDto;
import com.springboot.redtest.dto.user.UserDto;
import com.springboot.redtest.entity.favorite.FavoriteEntity;
import com.springboot.redtest.entity.like.LikeEntity;
import com.springboot.redtest.entity.quote.QuoteEntity;
import com.springboot.redtest.entity.user.UserEntity;
import com.springboot.redtest.exception.UserException;
import com.springboot.redtest.repository.favorite.FavoriteRepository;
import com.springboot.redtest.repository.quote.QuoteRepository;
import com.springboot.redtest.repository.user.UserRepository;
import com.springboot.redtest.service.favourite.FavoriteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteServiceImpl implements FavoriteService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Favorite a quote
     * @param favoriteDto
     */
    @Override
    public void favorite(FavoriteDto favoriteDto) {
        // Search for a user
        UserEntity userEntity = userRepository.findByPublicUserId(favoriteDto.getPublicUserId());

        // if user does not exists
        if (userEntity == null) throw new UserException("User does not exists");

        // Search for a quote
        QuoteEntity quoteEntity = quoteRepository.findByPublicQuoteId(favoriteDto.getPublicQuoteId());

        // if quote does not exists
        if (quoteEntity == null) throw new UserException("Quote does not exists");

        // Transfer data to userDto
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);

        // Set user for favorite
        favoriteDto.setUser(userDto);

        // Transfer data to quoteDto
        QuoteDto quoteDto = modelMapper.map(quoteEntity, QuoteDto.class);

        // Set quote for favorite
        favoriteDto.setQuote(quoteDto);

        // Transfer data to LikeEntity
        FavoriteEntity favoriteEntity = modelMapper.map(favoriteDto, FavoriteEntity.class);

        // save data to database
        favoriteRepository.save(favoriteEntity);
    }
}
