package com.canse.discord.services.impl;

import com.canse.discord.dto.NotificationDto;
import com.canse.discord.dto.RoleDto;
import com.canse.discord.models.Notification;
import com.canse.discord.repository.NotificationRepository;
import com.canse.discord.repository.RoleRepository;
import com.canse.discord.services.NotificationService;
import com.canse.discord.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final ObjectsValidator<NotificationDto> validator;

    @Override
    public Integer save(NotificationDto dto) {
        validator.validate(dto);
        Notification notification = NotificationDto.toEntity(dto);
        return repository.save(notification).getId();
    }

    @Override
    public List<NotificationDto> findAll() {
        return repository.findAll()
                .stream()
                .map(NotificationDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDto findById(Integer id) {
        return repository.findById(id)
                .map(NotificationDto::fromEntity)
                .orElseThrow( () -> new EntityNotFoundException("Notification inexistante"));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
