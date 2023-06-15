package com.canse.discord.controllers;

import com.canse.discord.dto.NotificationDto;
import com.canse.discord.services.NotificationService;
import com.canse.discord.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    @Autowired
    private NotificationService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody NotificationDto notificationDto){
        return ResponseEntity.ok(service.save(notificationDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<NotificationDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{notification-id}")
    public ResponseEntity<NotificationDto> findById(@PathVariable("notification-id") Integer notificationId){
        return ResponseEntity.ok(service.findById(notificationId));
    }

    @DeleteMapping("/{notification-id}")
    public ResponseEntity<NotificationDto> delete(@PathVariable("notification-id") Integer notificationId){
        service.delete(notificationId);
        return ResponseEntity.accepted().build();
    }

}
