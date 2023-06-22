package com.canse.discord.test_integration.controller;
import com.canse.discord.dto.UserDto;
import com.canse.discord.dto.auth.AuthenticationRequest;
import com.canse.discord.models.Role;
import com.canse.discord.models.User;
import com.canse.discord.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
    @MockBean
    private UserServiceImpl userService;

    @BeforeEach
    public void init() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }


    // DELETE
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void adminDeleteUser_WithSuccess() throws Exception {
        mvc.perform(delete("/user/1")).andExpect(status().isAccepted());
    }
    @Test
    @WithMockUser(roles = {"USER"})
    void userDeleteUser_WithException() throws Exception {
        mvc.perform(delete("/user/1")).andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(roles = {""})
    void roleUndefinedDeleteUser_WithException() throws Exception {
        mvc.perform(delete("/user/1")).andExpect(status().isForbidden());
    }

    // GET
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void adminGetUserUrlListeUSer_WithSuccess() throws Exception {
        mvc.perform(get("/user/")).andExpect(status().isOk());
    }
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void adminGetUserById_WithSuccess() throws Exception {
        mvc.perform(get("/user/1")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void adminGetUserListeUSer_WithSuccess() throws Exception {
        mvc.perform(get("/user/")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void UserGetUserUrlListeUSer_WithSuccess() throws Exception {
        mvc.perform(get("/user/")).andExpect(status().isOk());
    }

    @Test
    void utilisateurNonConectéGetUserlListeUSer_Exception() throws Exception {
        mvc.perform(get("/user/")).andExpect(status().isForbidden());
    }


}
