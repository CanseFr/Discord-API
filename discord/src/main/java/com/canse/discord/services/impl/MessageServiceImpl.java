package com.canse.discord.services.impl;

import com.canse.discord.dto.*;
import com.canse.discord.models.*;
import com.canse.discord.repository.*;
import com.canse.discord.services.MessageService;
import com.canse.discord.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;
    private final ChannelRepository repositoryChannel;
    private final UserRepository userRepository;

    private final ChannelServiceImpl channelService;
    private final ObjectsValidator<MessageDto> validator;


    @Override
    public Integer save(MessageDto dto) {
        // Validation du message
        //validator.validate(dto);

        // Recherche des relations
        Channel channel = repositoryChannel.findChannelById(dto.getChannelId());
        Optional<User> user = userRepository.findById(dto.getUserId());

        // Creation du message t enregistrement
        Message message = MessageDto.toEntity(dto);

        message.setChannel(channel);
        message.setUser(user.get());

        return repository.save(message).getId();

    }

    @Override
    public List<MessageDto> findAll() {
        return repository.findAll()
                .stream()
                .map(MessageDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MessageDto findById(Integer id) {
        return repository.findById(id)
                .map(MessageDto::fromEntity)
                .orElseThrow( () -> new EntityNotFoundException("Cet Id message n'existe pas : " + id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<MessageDto> findMessageByIdGroupeName(String groupe) {
        return repository.findMessagesByChannel_Name(groupe)
                .stream()
                .map(MessageDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> findMessageByIdChannel(Integer idChannel) {
        return repository.findMessagesByChannel_Id(idChannel)
                .stream()
                .map(MessageDto::fromEntity)
                .collect(Collectors.toList());
    }
}
