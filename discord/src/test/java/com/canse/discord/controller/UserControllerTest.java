package com.canse.discord.controller;
import com.canse.discord.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;


    @BeforeEach
    public void init() throws Exception {
        com.canse.discord.models.User user = new User();
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void adminConnectAppelUrlListeUSer_OkAttendu() throws Exception {
        mvc.perform(get("/user/")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void UserConnectAppelUrlListeUSer_OkAttendu() throws Exception {
        mvc.perform(get("/user/")).andExpect(status().isOk());
    }

    @Test
    void utilisateurNonConectéConnectAppelUrlListeUSer_OkAttendu() throws Exception {
        mvc.perform(get("/user/")).andExpect(status().isForbidden());
    }
    

}
