package com.canse.discord.test_unitaire.dto;

import com.canse.discord.dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class userDtoTest {

    UserDto user;

    @Before
    public void init() {
         user = new UserDto();
    }

    // FIRSTNAME
    @Test
    public void FirstnameToTrim_LastnameTrimed(){
        user.setFirstname("   julien    ");
        Assertions.assertEquals("Julien", user.getFirstname());
    }
    @Test
    public void FirstnameUpperCase_ToLowerCase(){
        user.setFirstname("   Julien    ");
        Assertions.assertEquals("Julien", user.getFirstname());
    }
    @Test
    public void FirstnameMiddleUpperCase_ToLowerCase(){
        user.setFirstname("   julIEn    ");
        Assertions.assertEquals("Julien", user.getFirstname());
    }

    // LASTNAME
    @Test
    public void LastnameToTrim_LastnameTrimed(){
        user.setLastname("   julien    ");
        Assertions.assertEquals("Julien", user.getLastname());
    }
    @Test
    public void LastnameUpperCase_ToLowerCase(){
        user.setLastname("   Julien    ");
        Assertions.assertEquals("Julien", user.getLastname());
    }
    @Test
    public void LastnameMiddleUpperCase_ToLowerCase(){
        user.setLastname("   juLIen    ");
        Assertions.assertEquals("Julien", user.getLastname());
    }

    // EMAIL
    @Test
    public void EmailToTrim_AdresseTrimed(){
        user.setEmail("   ju@ju.ju    ");
        Assertions.assertEquals("Ju@ju.ju", user.getEmail());
    }
    @Test
    public void EmailUpperCase_ToLowerCase(){
        user.setEmail("   JU@JU.JU    ");
        Assertions.assertEquals("Ju@ju.ju", user.getEmail());
    }
    @Test
    public void EmailMiddleUpperCase_ToLowerCase(){
        user.setEmail("   jU@JU.JU    ");
        Assertions.assertEquals("Ju@ju.ju", user.getEmail());
    }

}
