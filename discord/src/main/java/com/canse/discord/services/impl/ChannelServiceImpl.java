package com.canse.discord.services.impl;

import com.canse.discord.dto.ChannelDto;
import com.canse.discord.dto.UserDto;
import com.canse.discord.models.Channel;
import com.canse.discord.models.Groupe;
import com.canse.discord.models.User;
import com.canse.discord.repository.ChannelRepository;
import com.canse.discord.repository.GroupeRepository;
import com.canse.discord.repository.UserRepository;
import com.canse.discord.services.ChannelService;
import com.canse.discord.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final ObjectsValidator<ChannelDto> validator;

    @Override
    public Integer save(ChannelDto dto) {
        // Verfifier qu'il n'existe pas UID
        validator.validate(dto);
        Channel channel = ChannelDto.toEntity(dto);
        return channelRepository.save(channel).getId();
    }

    @Override
    public List<ChannelDto> findAll() {
        return channelRepository.findAll()
                .stream()
                .map(ChannelDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ChannelDto findById(Integer id) {
        return channelRepository.findById(id)
                .map(ChannelDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Ce channel n'existe pas "));
    }

    public Channel findByIdEntity(Integer id) {
        return channelRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + id));
    }

    @Override
    public void delete(Integer id) {
        channelRepository.deleteById(id);
    }

    @Override
    public Integer addUnitMemberToMemberListGroupe(Integer idUser, Integer idChannel) {
        User u = userRepository.findFirstById(idUser);
        Channel c = channelRepository.findChannelById(idChannel);
        c.addUserToListMemberUser(u);

        return channelRepository.save(c).getId();
    }

    @Override
    public Integer addAllUserOfGroupeToMemberListGroupe(Integer idGroupe, Integer idChannel) {
        List<User> userList = userRepository.findAllUserByGroupeId(idGroupe);
        Channel channel = channelRepository.findChannelById(idChannel);

        userList.forEach( user -> channel.addUserToListMemberUser(user));

        return channelRepository.save(channel).getId();
    }

    @Override
    public Integer deleteUniUserOfMemberList(Integer idChannel, Integer IdUser) {

        Channel c = channelRepository.findChannelById(idChannel);
        User u = userRepository.findFirstById(IdUser);
        c.removeUserToListMemberUser(u);

        return channelRepository.save(c).getId();
    }

    @Override
    public Integer updateChannelByGroupDelete(Integer idChannel, Integer idGroup) {
        Channel c = channelRepository.findChannelById(idChannel);
        List<User> listUser = userRepository.findAllUserByGroupeId(idGroup);

        listUser.forEach(user -> c.removeUserToListMemberUser(user));

        return channelRepository.save(c).getId();
    }

    @Override
    public List<UserDto> getAllUserByIdChannel(Integer idChannel) {
        return userRepository.findAllUserByChannelId(idChannel)
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Integer updateNameChannel(ChannelDto channelDto) {
        Channel c = channelRepository.findChannelById(channelDto.getId());
        c.setName(channelDto.getName());
        return channelRepository.save(c).getId();
    }
}
