package com.canse.discord.services.impl;

import com.canse.discord.dto.RoleDto;
import com.canse.discord.repository.RoleRepository;
import com.canse.discord.services.RoleService;
import com.canse.discord.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;
    private final ObjectsValidator<RoleDto> validator;

    @Override
    public Integer save(RoleDto dto) {

        return null;
    }

    @Override
    public List<RoleDto> findAll() {
        return null;
    }

    @Override
    public RoleDto findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
