package com.springboot.redtest.service.admin;

import com.springboot.redtest.entity.user.UserEntity;

public interface BlackListService {
    boolean isUserInBlackList(String email);

    void blockUser(String publicUserId);
}
