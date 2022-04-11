package com.springboot.redtest.service.user;

import com.springboot.redtest.dto.user.AddressDto;

public interface AddressService {

    /**
     * Create new address
     * @param publicUserId
     * @param addressDto (country, city)
     */
    void createAddress(String publicUserId, AddressDto addressDto);

    /**
     * Update an address
     * @param publicUserId
     * @param addressDto
     * @return
     */
    void updateAddress(String publicUserId, AddressDto addressDto);
}
