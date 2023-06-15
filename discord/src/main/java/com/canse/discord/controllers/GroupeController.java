package com.canse.discord.controllers;

import com.canse.discord.dto.GroupeDto;
import com.canse.discord.services.GroupeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groupe")
public class GroupeController {

    @Autowired
    private GroupeService service;

    @PostMapping("/") // TODO : Apres modification, les utilisateurs ne sont plus dans la liste, verifier
    public ResponseEntity<Integer> save(@RequestBody GroupeDto groupeDto){
        return ResponseEntity.ok(service.save(groupeDto));
    }

    @PatchMapping("/")
    public ResponseEntity<Integer> update(@RequestBody GroupeDto groupeDto){
        return ResponseEntity.ok((service.update(groupeDto)));
    }

    @PatchMapping("/patchname")
    public ResponseEntity<Integer> updateName(@RequestBody GroupeDto groupeDto){
        return ResponseEntity.ok((service.updateName(groupeDto)));
    }

    @PatchMapping("/deleteUserFromGroupe")
    public ResponseEntity<Integer> deleteUserFromeGroupe(@RequestBody GroupeDto groupeDto ){
        return ResponseEntity.ok(service.deleteUserFromGroupe(groupeDto.getId(), groupeDto.getIdUserToDelete()));
    }

    @GetMapping("/")
    public ResponseEntity<List<GroupeDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/allGroupeOfChannel/{id-channel}")
    public ResponseEntity<List<GroupeDto>> findAllGroupeOfChannel(@PathVariable("id-channel") Integer id){
        return ResponseEntity.ok(service.findAllGroupeOfChannel(id));
    }

    @GetMapping("/{id-groupe}")
    public ResponseEntity<GroupeDto> findById(@PathVariable("id-groupe") Integer groupeId){
        return ResponseEntity.ok(service.findById(groupeId));
    }

    @DeleteMapping("/{id-groupe}")
    public ResponseEntity<GroupeDto> delete(@PathVariable("id-groupe") Integer groupeId){
        service.delete(groupeId);
        return ResponseEntity.accepted().build();
    }

}
