package com.springboot.redtest.repository.like;

import com.springboot.redtest.entity.like.LikeEntity;
import com.springboot.redtest.entity.quote.QuoteEntity;
import com.springboot.redtest.entity.user.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends PagingAndSortingRepository<LikeEntity, Long> {
    /**
     * Search for a like quote
     * @param userEntity
     * @param quoteEntity
     * @return
     */
    LikeEntity findByUserAndQuote(UserEntity userEntity,  QuoteEntity quoteEntity);
}
