package com.springboot.redtest.response.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private String publicUserId;
    private String firstName;
    private String lastName;
    private String email;
    private String image;
    private AddressResponse address;
    boolean isBanned;
}
