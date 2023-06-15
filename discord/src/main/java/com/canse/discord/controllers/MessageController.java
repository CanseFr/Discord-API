package com.canse.discord.controllers;

import com.canse.discord.dto.MessageDto;
import com.canse.discord.services.MessageService;
import com.canse.discord.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    @Autowired
    private MessageService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody MessageDto messageDto){
        return ResponseEntity.ok(service.save(messageDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<MessageDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{message-id}")
    public ResponseEntity<MessageDto> findById(@PathVariable("message-id") Integer messageId){
        return ResponseEntity.ok(service.findById(messageId));
    }

    @DeleteMapping("/{message-id}")
    public ResponseEntity<MessageDto> delete(@PathVariable("message-id") Integer messageId){
        service.delete(messageId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/all/{channel-name}")
    public ResponseEntity<List<MessageDto>> getAllMessageOfAChannelByGroupeIdName(@PathVariable("channel-name") String groupeName){
        return ResponseEntity.ok(service.findMessageByIdGroupeName(groupeName));
    }

    @GetMapping("/getMessageByIdChannel/{channel-id}")
    public ResponseEntity<List<MessageDto>> getMessageByIdChannel(@PathVariable("channel-id") Integer idChannel){
        return ResponseEntity.ok(service.findMessageByIdChannel(idChannel));
    }

}
