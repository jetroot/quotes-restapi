package com.springboot.redtest.serviceimpl.admin;

import com.springboot.redtest.entity.blacklist.BlackListEntity;
import com.springboot.redtest.entity.user.UserEntity;
import com.springboot.redtest.exception.UserException;
import com.springboot.redtest.repository.blacklist.BlackListRepository;
import com.springboot.redtest.repository.user.UserRepository;
import com.springboot.redtest.service.admin.BlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlackListServiceImpl implements BlackListService {
    private final BlackListRepository blackListRepository;
    private final UserRepository userRepository;

    @Override
    public boolean isUserInBlackList(String email) {
        // find user by email
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) return false;

        // search for user in blacklist
        BlackListEntity blackListEntity = blackListRepository.findByUser(userEntity);

        return blackListEntity != null;
    }

    @Override
    public void blockUser(String publicUserId) {
        // search for a user
        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);

        // if user not found in users table
        if (userEntity == null) throw new UserException("User not found");

        // set user in blacklist
        BlackListEntity blackListEntity = new BlackListEntity();
        blackListEntity.setUser(userEntity);

        // block user
        blackListRepository.save(blackListEntity);
    }
}
