package com.canse.discord.services;

import com.canse.discord.dto.ChannelDto;
import com.canse.discord.dto.UserDto;
import com.canse.discord.models.User;

import java.util.List;

public interface ChannelService extends AbstractService<ChannelDto> {
    Integer addUnitMemberToMemberListGroupe(Integer idUser, Integer idChannel);
    Integer addAllUserOfGroupeToMemberListGroupe(Integer idGroupe, Integer idChannel);

    Integer deleteUniUserOfMemberList(Integer idChannel, Integer IdUser);
    Integer updateChannelByGroupDelete(Integer idChannel, Integer idGroup);

    List<UserDto> getAllUserByIdChannel(Integer idChannel);

    Integer updateNameChannel(ChannelDto channelDto);
}
