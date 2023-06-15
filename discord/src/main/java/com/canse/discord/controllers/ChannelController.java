package com.canse.discord.controllers;

import com.canse.discord.dto.ChannelDto;
import com.canse.discord.dto.UserDto;
import com.canse.discord.models.User;
import com.canse.discord.services.ChannelService;
import com.canse.discord.services.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channel")
@RequiredArgsConstructor
public class ChannelController {
    @Autowired
    private ChannelService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody ChannelDto channelDto){
        return ResponseEntity.ok(service.save(channelDto));
    }

    @PatchMapping("/updateNameChannel")
    public ResponseEntity<Integer> updateNameChannel(@RequestBody ChannelDto channelDto){
        return ResponseEntity.ok(service.updateNameChannel(channelDto));
    }

    @PatchMapping("/addUnitUserToChannel/{user-id}/{channel-id}")
    public ResponseEntity<Integer> updateChannelByUserUnit(@PathVariable("user-id") Integer userId,@PathVariable("channel-id") Integer channelId){
        return ResponseEntity.ok(service.addUnitMemberToMemberListGroupe(userId,channelId));
    }

    @PatchMapping("/addUnitGroupeToChannel/{groupe-id}/{channel-id}")
    public ResponseEntity<Integer> updateChannelByGroupeUnit(@PathVariable("groupe-id") Integer groupeId,@PathVariable("channel-id") Integer channelId){
        return ResponseEntity.ok(service.addAllUserOfGroupeToMemberListGroupe(groupeId,channelId));
    }

    @PatchMapping("/deleteUnitUserOfMemberList/{user-id}/{channel-id}")
    public ResponseEntity<Integer> deleteUnitUserOfMemberList(@PathVariable("user-id") Integer userId,@PathVariable("channel-id") Integer channelId){
        return ResponseEntity.ok(service.deleteUniUserOfMemberList(channelId,userId));
    }

    @PatchMapping("/updateMemberOfChannelDelete/{channel-id}/{groupe-id}")
    public ResponseEntity<Integer> updateMemberChannelByGroupDelete(@PathVariable("channel-id") Integer idChannel, @PathVariable("groupe-id") Integer idGroupe){
        return ResponseEntity.ok(service.updateChannelByGroupDelete(idChannel,idGroupe));
    }

    @GetMapping("/")
    public ResponseEntity<List<ChannelDto>> findAll(){return ResponseEntity.ok(service.findAll());}

    @GetMapping("/{channel-id}")
    public ResponseEntity<ChannelDto> findById(@PathVariable("channel-id") Integer channelId){
        return ResponseEntity.ok(service.findById(channelId));
    }

    @GetMapping("/allUserByIdChannel/{id-channel}")
    public ResponseEntity<List<UserDto>> getAllUserByIdChannel(@PathVariable("id-channel") Integer channelId){
        return ResponseEntity.ok(service.getAllUserByIdChannel(channelId));
    }

    @DeleteMapping("/{channel-id}")
    public ResponseEntity<ChannelDto> delete(@PathVariable("channel-id") Integer channelId){
        service.delete(channelId);
        return ResponseEntity.accepted().build();
    }
}
