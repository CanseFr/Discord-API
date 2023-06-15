package com.canse.discord.services;

import com.canse.discord.dto.MessageDto;
import com.canse.discord.models.Message;

import java.util.List;

public interface MessageService extends AbstractService<MessageDto> {

    List<MessageDto> findMessageByIdGroupeName(String groupe);
    List<MessageDto> findMessageByIdChannel(Integer idChannel);


}
