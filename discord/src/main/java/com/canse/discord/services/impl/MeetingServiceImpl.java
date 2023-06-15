package com.canse.discord.services.impl;

import com.canse.discord.dto.MeetingDto;
import com.canse.discord.models.Meeting;
import com.canse.discord.repository.MeetingRepository;
import com.canse.discord.services.MeetingService;
import com.canse.discord.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository repository;
    private final ObjectsValidator<MeetingDto> validator;

    @Override
    public Integer save(MeetingDto dto) {
        validator.validate(dto);
        Meeting meeting = MeetingDto.toEntity(dto);
        return repository.save(meeting).getId();
    }

    @Override
    public List<MeetingDto> findAll() {
        return repository.findAll()
                .stream()
                .map(MeetingDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MeetingDto findById(Integer id) {
        return repository.findById(id)
                .map(MeetingDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Ce meeting n'existe pas"));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
