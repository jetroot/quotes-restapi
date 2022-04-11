package com.springboot.redtest.custom;

import com.springboot.redtest.entity.user.UserEntity;
import com.springboot.redtest.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Load user by email
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Search for user in database
        UserEntity userEntity = userRepository.findByEmail(email);

        // If user does not exist
        if (userEntity == null) throw new UsernameNotFoundException("Email not found !");

        // return user if exists
        return new CustomUserDetails(userEntity);
    }

}
