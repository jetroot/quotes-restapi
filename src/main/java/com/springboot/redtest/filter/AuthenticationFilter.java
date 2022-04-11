package com.springboot.redtest.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.redtest.common.SpringApplicationContext;
import com.springboot.redtest.config.token.TokenConfig;
import com.springboot.redtest.custom.CustomUserDetails;
import com.springboot.redtest.dto.user.UserDto;
import com.springboot.redtest.request.user.UserLoginRequest;
import com.springboot.redtest.service.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserLoginRequest userLoginRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UserLoginRequest.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(),
                            userLoginRequest.getPassword(), new ArrayList<>())
            );

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        String email = ((CustomUserDetails) authentication.getPrincipal()).getUsername();

        // Search in database for that email
        UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
        UserDto userDto = userService.getUser(email);

        // List of roles
        ArrayList<String> roles = new ArrayList<>();
        roles.add(userDto.getRole().getRoleName());

        String token = Jwts.builder()
                .setSubject(email)
//                .claim("id", userDto.getPublicUserId())
//                .claim("roles", roles)
                .setExpiration(new Date(System.currentTimeMillis() + TokenConfig.TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, TokenConfig.TOKEN_SECRET )
                .compact();

        response.addHeader(TokenConfig.HEADER_AUTHORIZATION,TokenConfig .TOKEN_PREFIX + token);
        response.addHeader("id", userDto.getPublicUserId());
        response.addHeader("role", userDto.getRole().getRoleName());
        response.addHeader("access-control-expose-headers", "authorization, id, role");
    }
}
