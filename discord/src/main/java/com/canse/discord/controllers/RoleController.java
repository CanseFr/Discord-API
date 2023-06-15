package com.canse.discord.controllers;

import com.canse.discord.dto.RoleDto;
import com.canse.discord.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Role ne possede pas de save ni de delete.
 */

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController { // impossible de requeter les role
    @Autowired
    private RoleService service;

    @GetMapping("/")
    public ResponseEntity<List<RoleDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{role-id}")
    public ResponseEntity<RoleDto> findById(@PathVariable("role-id") Integer roleId){
        return ResponseEntity.ok(service.findById(roleId));
    }



}
