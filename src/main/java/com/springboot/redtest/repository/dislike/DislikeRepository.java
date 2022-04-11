package com.springboot.redtest.repository.dislike;

import com.springboot.redtest.entity.dislike.DislikeEntity;
import com.springboot.redtest.entity.quote.QuoteEntity;
import com.springboot.redtest.entity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DislikeRepository extends CrudRepository<DislikeEntity, Long> {
    /**
     * Search for a dislike quote
     * @param userEntity
     * @param quoteEntity
     * @return
     */
    DislikeEntity findByUserAndQuote(UserEntity userEntity, QuoteEntity quoteEntity);
}
