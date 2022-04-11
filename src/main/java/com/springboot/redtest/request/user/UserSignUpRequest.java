package com.springboot.redtest.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class UserSignUpRequest {
//    @Pattern(regexp = "\\A-Z+ | \\a-z+", message = "Firstname must contain only characters")
    @NotBlank(message="First name cannot be empty")
    @Size(min = 3, max = 20, message = "First name must be at least 3 characters")
    private String firstName;

    @NotBlank(message="Last name cannot be empty")
    @Size(min = 3, max = 20, message = "Last name must be at least 3 characters")
    private String lastName;

    @NotBlank(message="Email Cannot be empty")
    @Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", message = "Email does not respect email format")
    private String email;

    @NotBlank(message="Password cannot be empty")
    @Size(min = 8, max = 40, message="Password must be at least 8 characters")
    private String password;

}
