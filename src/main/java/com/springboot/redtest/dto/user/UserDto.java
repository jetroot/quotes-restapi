package com.springboot.redtest.dto.user;

import com.springboot.redtest.dto.role.RoleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1674332968690148134L;

    private long id;
    private String publicUserId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String image;
    private String encryptedPassword;
//    private List<RoleDto> roles = new ArrayList<>();
    private RoleDto role;
    private AddressDto addressDto;
    boolean isBanned = false;

}
