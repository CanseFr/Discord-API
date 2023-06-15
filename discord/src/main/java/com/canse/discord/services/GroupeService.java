package com.canse.discord.services;

import com.canse.discord.dto.GroupeDto;
import com.canse.discord.models.Groupe;
import com.canse.discord.models.User;

import java.util.List;

public interface GroupeService extends AbstractService<GroupeDto> {
    Integer update(GroupeDto groupeDto);
    Integer updateName(GroupeDto groupeDto);

    List<GroupeDto> findAllGroupeOfChannel(Integer idChannel);
    Integer deleteUserFromGroupe(Integer idGroupe, Integer idUser);

}
