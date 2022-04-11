package com.springboot.redtest.serviceimpl.admin;

import com.springboot.redtest.dto.user.UserDto;
import com.springboot.redtest.entity.user.UserEntity;
import com.springboot.redtest.repository.admin.AdminUsersRepository;
import com.springboot.redtest.service.admin.AdminUsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminUsersServiceImpl implements AdminUsersService {
    @Autowired
    private AdminUsersRepository adminUsersRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntities = adminUsersRepository.findAll();

        List<UserDto> usersDto = new ArrayList<>();

        for (UserEntity user : userEntities) {
            UserDto userDto = modelMapper.map(user, UserDto.class);

            if (user.getBlackList() != null) userDto.setBanned(true);

            usersDto.add(userDto);
        }

        return usersDto;
    }
}
