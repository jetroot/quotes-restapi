package com.springboot.redtest.dto.role;

import com.springboot.redtest.dto.user.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RoleDto {
    private long id;
    private String roleName;
    private UserDto user;
}
