package com.canse.discord.controllers;

import com.canse.discord.dto.MeetingDto;
import com.canse.discord.services.MeetingService;
import com.canse.discord.services.UserService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meeting")
@RequiredArgsConstructor
public class MeetingController {

    @Autowired
    private MeetingService service;

    @PostMapping("/")// Probleme creation a regler
    public ResponseEntity<Integer> save(@RequestBody MeetingDto meetingDto){
        return ResponseEntity.ok(service.save(meetingDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<MeetingDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{meeting-id}")
    public ResponseEntity<MeetingDto> findById(@PathVariable("meeting-id")Integer meetingId){
        return ResponseEntity.ok(service.findById(meetingId));
    }

    @DeleteMapping("/{meeting-id}")
    public ResponseEntity<MeetingDto> delete(@PathVariable("meeting-id")Integer meetingId){
        service.delete(meetingId);
        return ResponseEntity.accepted().build();
    }
}
