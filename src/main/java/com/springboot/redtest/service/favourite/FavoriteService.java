package com.springboot.redtest.service.favourite;

import com.springboot.redtest.dto.favorite.FavoriteDto;

public interface FavoriteService {
    /**
     * Favorite a quote
     * @param favoriteDto
     */
    void favorite(FavoriteDto favoriteDto);
}
