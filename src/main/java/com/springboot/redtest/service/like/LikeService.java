package com.springboot.redtest.service.like;

import com.springboot.redtest.dto.like.LikeDto;

public interface LikeService {
    /**
     * Like a quote
     * @param likeDto
     */
    void like(LikeDto likeDto);
}
