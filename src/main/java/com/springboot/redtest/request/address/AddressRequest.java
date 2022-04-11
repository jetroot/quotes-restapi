package com.springboot.redtest.request.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class AddressRequest {
    @NotBlank(message="Country cannot be empty")
    @Size(min = 3, max = 40, message="Maximum characters for country is 40 characters")
    private String country;

    @NotBlank(message="City cannot be empty")
    @Size(min = 3, max = 40, message="Maximum characters for city is 40 characters")
    private String city;
}
