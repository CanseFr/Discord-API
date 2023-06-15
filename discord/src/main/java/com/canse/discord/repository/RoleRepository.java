package com.canse.discord.repository;

import com.canse.discord.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String roleName);
    Role findFirstById(Integer id);
}
