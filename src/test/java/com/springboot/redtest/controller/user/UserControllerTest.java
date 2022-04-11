package com.springboot.redtest.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.redtest.request.user.UserSignUpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    private final String CREATE_NEW_USER_URI = "/users/createUser";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createUser() throws Exception {
        UserSignUpRequest userSignUpRequest = new UserSignUpRequest();
        userSignUpRequest.setFirstName("sofia");
        userSignUpRequest.setLastName("Tancredy");
        userSignUpRequest.setEmail("sofia@gmail.com");
        userSignUpRequest.setPassword("sofia1234");

        this.mockMvc.perform(
                post(CREATE_NEW_USER_URI, userSignUpRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(userSignUpRequest))
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

}