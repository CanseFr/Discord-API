package com.canse.discord.services.impl;

import com.canse.discord.dto.FileDto;
import com.canse.discord.dto.RoleDto;
import com.canse.discord.models.File;
import com.canse.discord.repository.FileRepository;
import com.canse.discord.repository.RoleRepository;
import com.canse.discord.services.FileService;
import com.canse.discord.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository repository;
    private final ObjectsValidator<FileDto> validator;

    @Override
    public Integer save(FileDto dto) {
        // renommer les fichier de maniere unique
        validator.validate(dto);
        File file = FileDto.toEntity(dto);
        return repository.save(file).getId();
    }

    @Override
    public List<FileDto> findAll() {
        return repository.findAll()
                .stream()
                .map(FileDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public FileDto findById(Integer id) {
        return repository.findById(id)
                .map(FileDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Ce fichier n'existe pas "));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
