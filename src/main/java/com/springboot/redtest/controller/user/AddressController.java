package com.springboot.redtest.controller.user;

import com.springboot.redtest.dto.user.AddressDto;
import com.springboot.redtest.request.address.AddressRequest;
import com.springboot.redtest.request.address.UpdateAddressRequest;
import com.springboot.redtest.service.user.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private ModelMapper modelMapper ;

    /**
     * Create new address
     * @param publicUserId
     * @param addressRequest (country, city)
     */
    @PostMapping(value = "/createAddress/{publicUserId}")
    public ResponseEntity<Void> createAddress(@PathVariable("publicUserId") String publicUserId, @Valid @RequestBody AddressRequest addressRequest) throws Exception {

        AddressDto addressDto = modelMapper.map(addressRequest, AddressDto.class);

        addressService.createAddress(publicUserId, addressDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Update an address
     * @param publicUserId
     * @param updateAddressRequest
     * @return
     */
    @PutMapping(path = "/{publicUserId}")
    public ResponseEntity<Void> updateAddress(@PathVariable("publicUserId") String publicUserId, @Valid @RequestBody UpdateAddressRequest updateAddressRequest) {

        AddressDto addressDto = modelMapper.map(updateAddressRequest, AddressDto.class);

        addressService.updateAddress(publicUserId, addressDto);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
