package com.canse.discord.controllers;

import com.canse.discord.dto.FileDto;
import com.canse.discord.dto.UserDto;
import com.canse.discord.services.FileService;
import com.canse.discord.services.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    @Autowired
    private FileService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody FileDto fileDto){
        return ResponseEntity.ok(service.save(fileDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<FileDto>> findAll(){return ResponseEntity.ok(service.findAll());}

    @GetMapping("/{file-id}")
    public ResponseEntity<FileDto> findById(@PathVariable("file-id") Integer fileId){
        return ResponseEntity.ok(service.findById(fileId));
    }

    @DeleteMapping("/{file-id}")
    public ResponseEntity<FileDto> delete(@PathVariable("file-id") Integer fileId){
        service.delete(fileId);
        return ResponseEntity.accepted().build();
    }

}
