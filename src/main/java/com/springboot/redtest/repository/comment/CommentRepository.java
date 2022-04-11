package com.springboot.redtest.repository.comment;

import com.springboot.redtest.entity.comment.CommentEntity;
import com.springboot.redtest.entity.quote.QuoteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<CommentEntity, Long> {
    /**
     * Find a comment
     * @param publicCommentId
     * @return
     */
    CommentEntity findByPublicCommentId(String publicCommentId);

    /**
     * Find all comments for a quote
     * @param pageable
     * @param quoteEntity
     * @return
     */
    Page<CommentEntity> findAllByQuote(Pageable pageable, QuoteEntity quoteEntity);
}
