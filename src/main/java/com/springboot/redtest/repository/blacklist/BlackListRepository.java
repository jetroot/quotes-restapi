package com.springboot.redtest.repository.blacklist;

import com.springboot.redtest.entity.blacklist.BlackListEntity;
import com.springboot.redtest.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListRepository extends JpaRepository<BlackListEntity, Long> {
    BlackListEntity findByUser(UserEntity userEntity);
}
