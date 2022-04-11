package com.springboot.redtest.controller.admin;

import com.springboot.redtest.dto.user.UserDto;
import com.springboot.redtest.response.user.UserResponse;
import com.springboot.redtest.service.admin.AdminUsersService;
import com.springboot.redtest.service.admin.BlackListService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private BlackListService blackListService;

    @Autowired
    private AdminUsersService adminUsersService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/block-user/{userId}")
    public ResponseEntity<Void> blockUser(@PathVariable("userId") String publicUserId) {

        blackListService.blockUser(publicUserId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/listUsers")
    public List<UserResponse> getAllUsers() {
        List<UserDto> usersDto =  adminUsersService.getAllUsers();

        List<UserResponse> userResponses = new ArrayList<>();

        for (UserDto user : usersDto) {
            UserResponse userResponse = modelMapper.map(user, UserResponse.class);
            userResponses.add(userResponse);
        }

        return userResponses;
    }

}
