package com.springboot.redtest.repository.user;

import com.springboot.redtest.entity.quote.QuoteEntity;
import com.springboot.redtest.entity.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    /**
     * Find a user by an email
     * @param email
     * @return
     */
    UserEntity findByEmail(String email);

    /**
     * Find a user by the public user id
     * @param userId
     * @return
     */
    UserEntity findByPublicUserId(String userId);

    /**
     * Find a user by id
     * @param id
     * @return
     */
    UserEntity findById(long id);
}