package com.springboot.redtest.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class UserLoginRequest {
    @NotBlank(message="Email Cannot be empty")
    @Email(message = "Email does not respect email format")
    private String email;

    @NotBlank(message="Password cannot be empty")
    @Size(min = 8, max = 40, message="Password must be at least 8 characters")
    private String password;
}
