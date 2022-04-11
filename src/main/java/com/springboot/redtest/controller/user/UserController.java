package com.springboot.redtest.controller.user;

import com.springboot.redtest.dto.user.UserDto;
import com.springboot.redtest.request.user.UserSignUpRequest;
import com.springboot.redtest.request.user.UserUpdateRequest;
import com.springboot.redtest.response.user.AddressResponse;
import com.springboot.redtest.response.user.UserResponse;
import com.springboot.redtest.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get user by its public id
     * @param userId
     * @return UserResponse
     */
    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("userId") String userId) {

        UserDto userDto = userService.getUserByPublicUserId(userId);

        // address response
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setCountry(userDto.getAddressDto().getCountry());
        addressResponse.setCity(userDto.getAddressDto().getCity());

        UserResponse userResponse = modelMapper.map(userDto, UserResponse.class);
        userResponse.setAddress(addressResponse);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    /**
     * Create new user
     * @param userSignUpRequest
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/createUser")
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserSignUpRequest userSignUpRequest) throws Exception {

        UserDto userDto = modelMapper.map(userSignUpRequest, UserDto.class);

        userService.createUser(userDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Update an existing user
     * @param userId
     * @param userUpdateRequest
     * @return
     */
    @PutMapping(path = "/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") String userId, @Valid @RequestBody UserUpdateRequest userUpdateRequest) {

        UserDto userDto = modelMapper.map(userUpdateRequest, UserDto.class);

        userService.updateUser(userId, userDto);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Upload user image
     * @param imageFile
     * @return
     */
    @PostMapping(value = "/uploadImageProfile/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadImageProfile(@PathVariable("userId") String userId,
                                                   @RequestParam("image") MultipartFile imageFile) {

        userService.uploadUserImage(userId, imageFile);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
