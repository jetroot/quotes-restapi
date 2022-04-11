package com.springboot.redtest.repository.quote;

import com.springboot.redtest.entity.quote.QuoteEntity;
import com.springboot.redtest.entity.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends PagingAndSortingRepository<QuoteEntity, Long> {
    /**
     * Find a quote by public quote id
     * @param quoteId
     * @return
     */
    QuoteEntity findByPublicQuoteId(String quoteId);

    /**
     * Find a user by a quote
     * @param quoteEntity
     * @return
     */
//    UserEntity findByUser(QuoteEntity quoteEntity);

    /**
     * Get all quotes
     * @param pageable
     * @return
     */
    Page<QuoteEntity> findAll(Pageable pageable);

}