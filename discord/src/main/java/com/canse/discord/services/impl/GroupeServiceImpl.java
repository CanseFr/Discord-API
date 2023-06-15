package com.canse.discord.services.impl;

import com.canse.discord.dto.GroupeDto;
import com.canse.discord.models.Groupe;
import com.canse.discord.models.User;
import com.canse.discord.repository.GroupeRepository;
import com.canse.discord.repository.UserRepository;
import com.canse.discord.services.GroupeService;
import com.canse.discord.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupeServiceImpl implements GroupeService {
    private final GroupeRepository groupeRepository;
    private final UserRepository userRepository;
    private final ObjectsValidator<GroupeDto> validator;

    @Override
    public Integer save(GroupeDto dto) {
        // TODO :  Verifier qu'il n'existe pas
        validator.validate(dto);
        // TODO :  recuperer la liste user pour la redonner
        Groupe groupe = GroupeDto.toEntity(dto);
        return groupeRepository.save(groupe).getId();
    }

    @Override
    public List<GroupeDto> findAll() {
        return groupeRepository.findAll()
                .stream()
                .map(GroupeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public GroupeDto findById(Integer id) {
        return groupeRepository.findById(id)
                .map(GroupeDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Ce groupe n'existe pas "));
    }

    @Override
    public void delete(Integer id) {
        groupeRepository.deleteById(id);
    }


    @Override
    public Integer update(GroupeDto groupeDto) {
        User u = userRepository.findFirstById(groupeDto.getIdUserToAdd());
        Groupe g = groupeRepository.findFirstByName(groupeDto.getName());
        g.addUserToListUser(u);
        return groupeRepository.save(g).getId();
    }

    @Override
    public Integer updateName(GroupeDto groupeDto) {
        Groupe g = groupeRepository.findById(groupeDto.getId()).get();
        g.setName(groupeDto.getName());
        return groupeRepository.save(g).getId();
    }

    @Override
    public List<GroupeDto> findAllGroupeOfChannel(Integer idChannel) {

        return groupeRepository.findAllGroupeContainInChannelByIdChannel(idChannel)
                .stream()
                .map(GroupeDto::fromEntityFinest)
                .collect(Collectors.toList());
    }

    @Override
    public Integer deleteUserFromGroupe(Integer idGroupe, Integer idUser) {
        User u = userRepository.findFirstById(idUser);
        Groupe g = groupeRepository.findById(idGroupe).get();
        g.deleteUserToListUser(u);
        return groupeRepository.save(g).getId();
    }


}
