package com.canse.discord;
import com.canse.discord.dto.UserDto;
import com.canse.discord.models.Role;
import com.canse.discord.models.User;
import com.canse.discord.repository.UserRepository;
import com.canse.discord.services.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
class DiscordApplicationTests {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    @MockBean
    private UserServiceImpl userService;

    @BeforeEach
    public void init() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

        UserDto userBeaforEach = UserDto.builder()
                .email("ssssadam@hussein.sisi")
                .password("salamalek")
                .firstname("hussein")
                .lastname("sadame")
                .roles(new ArrayList<>(List.of(new Role(1,"ROLE_ADMIN"))))
                .build();

        Mockito.when(userService.findById(1)).thenReturn(userBeaforEach);
    }
    //______________USER MOCK TEST LOGIN
    @Test
    public void testLoginMock_Success() throws Exception {
        String loginRequestJson = "{\"email\":\"ssssm@hussein.sisi\",\"password\":\"saalek\"}";

        mvc.perform(post("/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequestJson))
                .andExpect(status().isOk());
    }
//______________USER MOCK TEST LOGIN







//
//    @WithMockUser(username = "admin", roles = {"ADMIN"})
//    @Test
//    public void homePageReserveToAdminDoitRetournerStatut200() throws Exception{
//        mvc.perform(get("/user/")).andExpect(status().isOk());
//    }



//	@Test
//	void appelUrlRacine_Message() throws Exception {
//		mvc.perform(get("/")).andExpect(content().string("Serveur ok mais rien sur cet URL"));
//	}
//
//	@Test
//	void utilisateurNonConectéAppelUrlListUser_Attendu403() throws Exception {
//		mvc.perform(get("/user/")).andExpect(status().isForbidden());
//	}
//



}
