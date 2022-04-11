package com.springboot.redtest.service.admin;

import com.springboot.redtest.dto.user.UserDto;

import java.util.List;

public interface AdminUsersService {
    /**
     * List all users
     * @return list of users
     */
    List<UserDto> getAllUsers();
}
