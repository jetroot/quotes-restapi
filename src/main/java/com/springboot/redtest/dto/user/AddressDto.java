package com.springboot.redtest.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class AddressDto implements Serializable {
    private static final long serialVersionUID = 1816387721252158297L;

    private String country;
    private String city;
    private UserDto user;

}
