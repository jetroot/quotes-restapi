package com.springboot.redtest.repository.admin;

import com.springboot.redtest.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminUsersRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAll();
}
