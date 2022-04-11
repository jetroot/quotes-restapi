package com.springboot.redtest.service.dislike;

import com.springboot.redtest.dto.dislike.DislikeDto;

public interface DislikeService {
    /**
     * Dislike a quote
     * @param dislikeDto
     */
    void dislike(DislikeDto dislikeDto);
}
