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


//	@Test
//	void appelUrlRacine_Message() throws Exception {
//		mvc.perform(get("/")).andExpect(content().string("Serveur ok mais rien sur cet URL"));
//	}
//


//    @Test
//    void listUsers() throws Exception {
//        Map<String, Object> claims = new HashMap<>();
//        User u = new User("test","test",true, true, true, true, Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
//        JwtUtils jwtUtils = new JwtUtils();
//        String token = jwtUtils.createToken(claims,u);
//        String bearerToken = "Bearer " + token;
//
//    }

//    @Test
//    @WithMockUser(username = "admin@admin.admin", password = "testtesttest")
//    public void mytest1() throws Exception {
//        mockMvc.perform(post("/auth/authenticate").header("X-Requested-With", "Origin", "Content-Type","Accept", "Authorization","Access-Control-Allow-Origin"))
//                .andExpect(status().isOk());
//
//    }


//_____________________________________________________________________


//    @Test
//    public void testGetUser() throws Exception{
//        Map<String, Object> claims = new HashMap<>();
//
//        String token = jwtUtils.generateToken(new User(), claims);
//        mockMvc.perform(get("/user/").header("Authorization", "Bearer " + token))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$[0].firstname", is("test")));
//    }

//_____________________________________________________________________
//    @Test
//    public void testConnection() throws Exception{
//        Map<String, Object> claims = new HashMap<>();
//
//        User u = new User("test","test",true, true, true, true, Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
//         JwtUtils jwtUtils = new JwtUtils();
//        String token = jwtUtils.createToken(claims,u);
//        String bearerToken = "Bearer " + token;
//_____________________________________________________________________

//        mockMvc.perform(get("/user").header("token",token)).andExpect(status().isOk());

//_____________________________________________________________________
//            final HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Type", MediaType.APPLICATION_XML_VALUE); // recuf config
//            final ResponseEntity<Customer> response = template.exchange(
//                    String.format("http://localhost:%d/api/customers", port),
//                    HttpMethod.POST,
//                    new HttpEntity<>(newCustomer, headers),
//                    Customer.class
//            );
//            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//            assertThat(response.getBody()).isNull();
//            assertThat(response.getHeaders().getLocation().toString()).isEqualTo(String.format("http://localhost:%d/api/customers/0", port));
//_____________________________________________________________________

//         AuthenticationRequest auth = new AuthenticationRequest();
//         auth.setEmail("canse@canse.canse");
//         auth.setPassword("testtesttest");
//
//
//
//        mockMvc.perform(post("/auth/authenticate")
//                        .content(MediaType.APPLICATION_JSON))
//
//                .andExpect(status().isOk()).andReturn();
//    }


}
