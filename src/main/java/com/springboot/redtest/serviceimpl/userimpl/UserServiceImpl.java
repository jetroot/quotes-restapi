package com.springboot.redtest.serviceimpl.userimpl;

import com.springboot.redtest.common.Utility;
import com.springboot.redtest.config.password.PasswordConfig;
import com.springboot.redtest.dto.role.RoleDto;
import com.springboot.redtest.dto.user.AddressDto;
import com.springboot.redtest.dto.user.UserDto;
import com.springboot.redtest.entity.role.RoleEntity;
import com.springboot.redtest.entity.user.UserEntity;
import com.springboot.redtest.exception.UserException;
import com.springboot.redtest.repository.role.RoleRepository;
import com.springboot.redtest.repository.user.UserRepository;
import com.springboot.redtest.service.admin.BlackListService;
import com.springboot.redtest.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BlackListService blackListService;

    // Get uploaded directory of images
    @Value("${uploaded_images_config}")
    private String FILE_DIRECTORY ;

    /**
     * Get user by its public user id
     * @param userId
     * @return UserDto
     */
    public UserDto getUserByPublicUserId(String userId) {
        // Get user by its id
        UserEntity userEntity = userRepository.findByPublicUserId(userId);

        // Check if user exists
        if (userEntity == null) throw new UserException("User with id: " + userId + " not found !");

        // and add uploaded image directory to image
        userEntity.setImage(userEntity.getImage());


//        addressDto.setCity(userEntity.getAddress().getCity() != null ? userEntity.getAddress().getCity() : "");
//        addressDto.setCountry(userEntity.getAddress().getCountry() != null ? userEntity.getAddress().getCountry()  : "");

        // Address for this user
        AddressDto addressDto = new AddressDto();

        if (userEntity.getAddress() != null) {
            addressDto.setCity(userEntity.getAddress().getCity());
            addressDto.setCountry(userEntity.getAddress().getCountry());
        } else {
            addressDto.setCity("");
            addressDto.setCountry("");
        }

        // Transfer user object from entity to dto
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        userDto.setAddressDto(addressDto);

        // Return that user which is userDto
        return userDto;
    }

    /**
     * Create new user AKA SignUp
     * @param userDto : User Info (firstName, lastName, email, password)
     * @return
     */
    @Override
    public void createUser(UserDto userDto) {
        boolean isUserBanned = blackListService.isUserInBlackList(userDto.getEmail());

        // check if user is in blacklist
        if (isUserBanned) throw new UserException("User has banned !");

        // Check if email is already taken
        UserEntity checkUserEmail = userRepository.findByEmail(userDto.getEmail());

        // if email exists
        if (checkUserEmail != null) throw new UserException("Email is already exists !");

        // set role
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName("ROLE_USER");

        // Search for role in db
        RoleEntity roleEntity = roleRepository.findByRoleName(roleDto.getRoleName());

        // If role does not exists in db
        if (roleEntity == null) throw new UserException("Role does not exists");

        // transfer data to user entity
        UserEntity userEntity =  modelMapper.map(userDto, UserEntity.class);

        // Set Public User Id
        userEntity.setPublicUserId(Utility.UUID16());

        // Set Encrypted Password
        userEntity.setEncryptedPassword(passwordConfig.bPasswordEncoder().encode(userDto.getPassword()));

        // Set role for this user
        userEntity.setRole(roleEntity);

        // Save data to database
        userRepository.save(userEntity);

    }

    /**
     * Get user by email
     * @param email
     * @return
     */
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        System.out.println("userEntity: " + userEntity);

        if (userEntity == null) throw new UserException("Email: " + email + " not found !");

        ModelMapper modelMapper = new ModelMapper();

        UserDto userDto  = modelMapper.map(userEntity, UserDto.class);

        return userDto;
    }

    /**
     *  Update user in database
     * @param userId
     * @param userDto : User Info (firstName, lastName, password)
     */
    @Override
    public void updateUser(String userId, UserDto userDto) {
        // Search for user in database
        UserEntity userEntity = userRepository.findByPublicUserId(userId);

        // If user does not exists
        if (userEntity == null) throw new UserException("User with Id: " + userId + " not found !");

        // Check first name is not blank
        if (userDto.getFirstName() != null && !userDto.getFirstName().isBlank()) {
            userEntity.setFirstName(userDto.getFirstName());
        }

        // Check last name is not blank
        if (userDto.getLastName() != null && !userDto.getLastName().isBlank()) {
            userEntity.setLastName(userDto.getLastName());
        }

        // Check password is not blank
        if (userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
            userEntity.setEncryptedPassword(passwordConfig.bPasswordEncoder().encode(userDto.getPassword()));
        }

        // save data to database
        userRepository.save(userEntity);

    }

    /**
     * Upload user image to the server
     * and save the path of image to the database
     * @param userId
     * @param imageFile
     */
    @Override
    public void uploadUserImage(String userId, MultipartFile imageFile) {
        // check image extension
        if (!Utility.checkImageExtension(imageFile.getContentType())) throw new UserException("Only .jpg and .png .jpeg and .gif extensions are allowed");

        // Search for user in database
        UserEntity userEntity = userRepository.findByPublicUserId(userId);

        // If user does not exists
        if (userEntity == null) throw new UserException("User with Id: " + userId + " not found !");

        // If user has changed image before
        if (userEntity.getImage() != null) {
            // Get current image
            String currentImage = userEntity.getImage();

            // Instantiate File
            File file = new File(FILE_DIRECTORY + currentImage);

            // Remove image from the server
            if (file.exists() && file.delete()) {
                // Upload Image to server and save it to database
                writeImageAndSave(userEntity, imageFile);

                // Stop method fro goes on
                return;
            }

            // Throw an error if image didn't uploaded to the server
            throw new UserException("Could not upload the image");

        }

        // Upload Image to server and save it to database
        writeImageAndSave(userEntity, imageFile);

    }

    /**
     * Upload Image to server and save it to database
     * @param userEntity : Entity that will save to database
     * @param imageFile : The image that will uploaded to the server
     */
    public void writeImageAndSave(UserEntity userEntity, MultipartFile imageFile) {
        // File Output Stream which writes bytes to the server
        FileOutputStream fileOutputStream = null;

        try {
            String img = imageFile.getOriginalFilename();
            String[] arr = img.split("\\.");
            // name of image
            String imageName = Utility.UUID25() + "." + arr[1];

            // Upload image to the server
            fileOutputStream = new FileOutputStream(FILE_DIRECTORY + imageName);
            fileOutputStream.write(imageFile.getBytes());
            fileOutputStream.close();

            // set image
            userEntity.setImage(imageName);

            // save data to database
            userRepository.save(userEntity);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
