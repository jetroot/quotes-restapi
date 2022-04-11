package com.springboot.redtest.controller.favorite;

import com.springboot.redtest.dto.favorite.FavoriteDto;
import com.springboot.redtest.request.favorite.FavoriteRequest;
import com.springboot.redtest.service.favourite.FavoriteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * mark a quote as favorite
     * @param favoriteRequest
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> makeFavourite(@Valid @RequestBody FavoriteRequest favoriteRequest) {

        FavoriteDto favoriteDto = modelMapper.map(favoriteRequest, FavoriteDto.class);

        favoriteService.favorite(favoriteDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
