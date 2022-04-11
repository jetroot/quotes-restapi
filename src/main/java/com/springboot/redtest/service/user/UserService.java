package com.springboot.redtest.service.user;

import com.springboot.redtest.dto.user.UserDto;
import com.springboot.redtest.entity.user.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 * This interface is about UserService
 *
 */
public interface UserService {
    /**
     * Get user by its public user id
     * @param userId
     * @return UserDto
     */
    UserDto getUserByPublicUserId(String userId);

    /**
     * Create new user
     * @param userDto Object
     * @return userDto
     */
    void createUser(UserDto userDto);

    /**
     * Get user by email
     * @param email
     * @return userDto
     */
    UserDto getUser(String email);

    /**
     * Update user
     * @param userId
     * @param userUpdateRequest
     * @return
     */
    void updateUser(String userId, UserDto userUpdateRequest);

    /**
     * Check user imag and call writeImageAndSave method
     * and save the path of image to the database
     * @param userId
     * @param imageFile
     */
    void uploadUserImage(String userId, MultipartFile imageFile);

    /**
     * Upload Image to server and save it to database
     * @param userEntity : Entity that will save to database
     * @param imageFile : The image that will uploaded to the server
     */
    void writeImageAndSave(UserEntity userEntity, MultipartFile imageFile);
}
