package com.canse.discord;

import com.canse.discord.controllers.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
class DiscordApplicationTests {

//	@Autowired
//	private WebApplicationContext context;
//	private MockMvc mvc;
//	@BeforeEach
//	public void setup() {
//		mvc = MockMvcBuilders
//				.webAppContextSetup(context)
//				.apply(springSecurity())
//				.build();
//	}

//	@Test
//	void appelUrlRacine_OkAttendu() throws Exception {
//		mvc.perform(get("/auth/authenticate")).andExpect(status().isOk());
//	}

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
//	@Test
//	@WithMockUser(roles = {"USER"})
//	void utilisateurConnectAppelUrlListeUSer_OkAttendu() throws Exception {
//		mvc.perform(get("/user/")).andExpect(status().isOk());
//	}
//
//	@Test
//	@WithMockUser(roles = {"ADMIN"})
//	void adminConnectAppelUrlListeUSer_OkAttendu() throws Exception {
//		mvc.perform(get("/user/")).andExpect(status().isOk());
//	}


	//__________________________________________________________________________________



}
