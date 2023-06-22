package com.canse.discord.test_integration.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AuthControllerTest {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;

	@BeforeEach
	public void init() throws Exception {
		this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
	public void testLoginRightEmail_Success() throws Exception {

		String loginRequestJson = "{\"email\":\"admin@admin.admin\",\"password\":\"testtesttest\"}";

		mvc.perform(post("/auth/authenticate")
						.contentType(MediaType.APPLICATION_JSON)
						.content(loginRequestJson))
				.andExpect(status().isOk());
	}

	@Test
	public void testLoginWrongEmail_Fail() throws Exception {

		String loginRequestJson = "{\"email\":\"edmin@admin.admin\",\"password\":\"testtesttest\"}";

		mvc.perform(post("/auth/authenticate")
						.contentType(MediaType.APPLICATION_JSON)
						.content(loginRequestJson))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testLoginWrongPassword_Fail() throws Exception {

		String loginRequestJson = "{\"email\":\"admin@admin.admin\",\"password\":\"testtesttestt\"}";

		mvc.perform(post("/auth/authenticate")
						.contentType(MediaType.APPLICATION_JSON)
						.content(loginRequestJson))
				.andExpect(status().isBadRequest());
	}
}
