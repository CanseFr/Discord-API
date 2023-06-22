package com.canse.discord.test_unitaire.dto;

import com.canse.discord.dto.GroupeDto;
import com.canse.discord.dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class groupeDtoTest {
    GroupeDto groupeDto;

    @Before
    public void init() {
        groupeDto = new GroupeDto();
    }

    // NAME
    @Test
    public void FirstnameToTrim_LastnameTrimed(){
        groupeDto.setName("   name    ");
        Assertions.assertEquals("Name", groupeDto.getName());
    }
    @Test
    public void FirstnameUpperCase_ToLowerCase(){
        groupeDto.setName("   NAME    ");
        Assertions.assertEquals("Name", groupeDto.getName());
    }
    @Test
    public void FirstnameMiddleUpperCase_ToLowerCase(){
        groupeDto.setName("   naME    ");
        Assertions.assertEquals("Name", groupeDto.getName());
    }

}
