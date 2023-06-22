package com.canse.discord.controllers;

import com.canse.discord.dto.UserDto;
import com.canse.discord.dto.auth.AuthenticationRequest;
import com.canse.discord.dto.auth.AuthenticationResponse;
import com.canse.discord.models.User;
import com.canse.discord.repository.UserRepository;
import com.canse.discord.services.UserService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(service.save(userDto));
    }

    @PostMapping("/addunituserbyadmin")
    public ResponseEntity<Integer> addUnitUserByAdmin(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(service.registerUnitUserByAdmin(userDto));
    }

    @PostMapping("/addUsersByCsvFile")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Integer> addListUserByAdminNoGroup (@RequestBody List<User> userList) {
        return ResponseEntity.ok(service.registerListUserByAdminNoGroupe(userList));
    }

    @PatchMapping("/")
    public ResponseEntity<Integer> updateUserInfoByAdmin (@RequestBody UserDto userDto){
        return ResponseEntity.ok(service.modifyUserInfoByAdmin(userDto));
    }

    @PatchMapping("/validate/{user-id}")
    public ResponseEntity<Integer> validateAccount (@PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(service.validateAccount(userId));
    }


    @PatchMapping("/invalidate/{user-id}")
    public ResponseEntity<Integer> invalidateAccount (@PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(service.invalidateAccount(userId));
    }


    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll () {
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping("/allbygroupe/{groupe-name}")
    public ResponseEntity<List<User>> findAllByGroupe (@PathVariable("groupe-name") String groupeName){
        return ResponseEntity.ok(userRepository.findAllUserByGourpeName(groupeName));
    }

    @GetMapping("/allbyid/{groupe-id}")
    public ResponseEntity<List<User>> findAllByGroupe (@PathVariable("groupe-id") Integer groupeId){
        return ResponseEntity.ok(userRepository.findAllUserByGroupeId(groupeId));
    }


    @GetMapping("/{user-id}")
    public ResponseEntity<UserDto> findById (@PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(service.findById(userId));
    }

    @DeleteMapping("/{user-id}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<UserDto> delete (@PathVariable("user-id") Integer userId){
        service.delete(userId);
        return ResponseEntity.accepted().build();
    }


//    // REGISTER AND AUTHENTICATE
//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register (@RequestBody UserDto user){
//        return ResponseEntity.ok(service.register(user));
//    }
//
//@PostMapping("/authenticate")
//public ResponseEntity<?> authenticate (@RequestBody AuthenticationRequest request){
//    return ResponseEntity.ok(service.authenticate(request));
//}

}

