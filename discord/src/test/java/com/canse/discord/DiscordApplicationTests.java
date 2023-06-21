package com.canse.discord;
import com.canse.discord.models.User;
import com.canse.discord.repository.UserRepository;
import com.canse.discord.services.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
class DiscordApplicationTests {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;
	@Autowired
	private ObjectMapper mapper;
	@Mock
	private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    private static final String ENDPOINT = "/user/";

    @BeforeEach
    public void init() throws Exception {
        User user = new User();
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }


//    @Test
//    void getUser_whenLastnameExistInBDD_shouldReturnAUserAndHttpStatusOk() throws Exception {
//        String lastname = "test";
//
//        mvc .perform(get(new StringBuilder(ENDPOINT)
//                        .append(lastname).toString()))
//
//                .andExpect(status().isOk());
//
//    }

//
//	@BeforeEach
//	public void setup() {
//		mvc = MockMvcBuilders
//				.webAppContextSetup(context)
//				.apply(springSecurity())
//				.build();
//	}
//
//
//    @WithMockUser(username = "admin", roles = {"ADMIN"})
//    @Test
//    public void homePageReserveToAdminDoitRetournerStatut200() throws Exception{
//        mvc.perform(get("/user/")).andExpect(status().isOk());
//    }

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

//__________________________________________________________________________________


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
