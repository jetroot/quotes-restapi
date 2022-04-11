package com.springboot.redtest.serviceimpl.userimpl;

import com.springboot.redtest.dto.user.AddressDto;
import com.springboot.redtest.dto.user.UserDto;
import com.springboot.redtest.entity.user.AddressEntity;
import com.springboot.redtest.entity.user.UserEntity;
import com.springboot.redtest.exception.UserException;
import com.springboot.redtest.repository.user.AddressRepository;
import com.springboot.redtest.repository.user.UserRepository;
import com.springboot.redtest.service.user.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Create new address
     * @param publicUserId
     * @param addressDto (country, city)
     */
    @Override
    public void createAddress(String publicUserId, AddressDto addressDto) {
        // Find user by public user id
        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);

        // if public user id does not exists
        if (userEntity == null) throw new UserException("User id does not exists !");

        // Check if user has already an address
        AddressEntity hasAddress = addressRepository.findByUser(userEntity);

        // if user has already an address
        if (hasAddress != null) throw new UserException("You already have an address");

        // Transfer data to UserDto
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);

        // Set Address Object
        addressDto.setUser(userDto);

        // Transfer data to AddressEntity
        AddressEntity addressEntity = modelMapper.map(addressDto, AddressEntity.class);

        // save data to database
        addressRepository.save(addressEntity);

    }

    /**
     * Update an address
     * @param publicUserId
     * @param addressDto
     * @return
     */
    @Override
    public void updateAddress(String publicUserId, AddressDto addressDto) {
        // Search for user in database
        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);

        // If user does not exists
        if (userEntity == null) throw new UsernameNotFoundException("User with Id: " + publicUserId + " not found !");

        // Search for address by userEntity
        AddressEntity addressEntity = addressRepository.findByUser(userEntity);

        // Check country is not blank
        if (addressDto.getCountry() != null && !addressDto.getCountry().isBlank()) {
            addressEntity.setCountry(addressDto.getCountry());
        }

        // Check city is not blank
        if (addressDto.getCity() != null && !addressDto.getCity().isBlank()) {
            addressEntity.setCity(addressDto.getCity());
        }

        // save data to database
        addressRepository.save(addressEntity);
    }
}
