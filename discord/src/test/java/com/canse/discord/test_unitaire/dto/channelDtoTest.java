package com.canse.discord.test_unitaire.dto;

import com.canse.discord.dto.ChannelDto;
import com.canse.discord.dto.GroupeDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class channelDtoTest {
    ChannelDto channelDto;

    @Before
    public void init() {
        channelDto = new ChannelDto();
    }

    // NAME
    @Test
    public void FirstnameToTrim_LastnameTrimed(){
        channelDto.setName("   name    ");
        Assertions.assertEquals("Name", channelDto.getName());
    }
    @Test
    public void FirstnameUpperCase_ToLowerCase(){
        channelDto.setName("   NAME    ");
        Assertions.assertEquals("Name", channelDto.getName());
    }
    @Test
    public void FirstnameMiddleUpperCase_ToLowerCase(){
        channelDto.setName("   naME    ");
        Assertions.assertEquals("Name", channelDto.getName());
    }
}
