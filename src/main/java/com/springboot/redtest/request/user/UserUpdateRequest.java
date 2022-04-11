package com.springboot.redtest.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class UserUpdateRequest {
    @Size(min = 3, max = 20, message = "First name must be at least 3 characters")
    private String firstName;

    @Size(min = 3, max = 20, message = "Last name must be at least 3 characters")
    private String lastName;

    @Size(min = 8, max = 40, message="Password must be at least 8 characters")
    private String password;
}
