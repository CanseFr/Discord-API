package com.canse.discord.services.impl;

import com.canse.discord.config.JwtUtils;
import com.canse.discord.dto.UserDto;
import com.canse.discord.dto.auth.AuthenticationRequest;
import com.canse.discord.dto.auth.AuthenticationResponse;
import com.canse.discord.models.Channel;
import com.canse.discord.models.Groupe;
import com.canse.discord.models.Role;
import com.canse.discord.models.User;
import com.canse.discord.repository.ChannelRepository;
import com.canse.discord.repository.GroupeRepository;
import com.canse.discord.repository.RoleRepository;
import com.canse.discord.repository.UserRepository;
import com.canse.discord.services.UserService;
import com.canse.discord.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;
    private final GroupeRepository groupeRepository;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;
    private final ObjectsValidator<UserDto> validator;
    private final PasswordEncoder passwordEncoder;

    private final EmailServiceImpl emailService;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }
    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun utilisateur trouvé avec cet Id : " + id));
    }
    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Integer validateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aucun user trouvé en base pour la validation"));

        user.setActive(true);
        userRepository.save(user);
        return user.getId();
    }
    @Override
    public Integer invalidateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aucun user trouvé en base pour l'invalidation"));
        user.setActive(false);
        userRepository.save(user);
        return user.getId();
    }
    @Override
    @Transactional
    public AuthenticationResponse register(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("Mot de passe generé -> "+user.getPassword()); // Pour info

        List<Role> roleList = new ArrayList<>();
        Optional<Role> role = roleRepository.findById(2);
        roleList.add(role.get());
        user.setRoles(roleList);

        var savedUser =  userRepository.save(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getId());
        claims.put("fullname", savedUser.getFirstname() + " " + savedUser.getLastname());

        String token = jwtUtils.generateToken(savedUser,claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

@Override
public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
    final User user;
    // CHECK MAIL
    try{
        user= (User) userRepository.findByEmail(request.getEmail()).get();
    } catch (Exception e){
        return ResponseEntity.notFound().build();
    }

    // CHECK Auth Mdp
    try{
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
    } catch (AuthenticationException e){
        return ResponseEntity.badRequest().build();
    }

    // Si ok
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", user.getId());
    claims.put("fullName", user.getFirstname() + " " + user.getLastname());
    final String token = jwtUtils.generateToken(user,claims);

    return ResponseEntity.ok( AuthenticationResponse.builder().token(token).build());
}

    @Override
    public List<User> findUserByGroupeName(String groupe) {
        return userRepository.findAllUserByGourpeName(groupe);
    }

    @Override
    public List<User> findAllUserByGroupeId(Integer idGroupe) {
        return userRepository.findAllUserByGroupeId(idGroupe);
    }


    private Role findOrCreateRole(String roleName){
        Role role = roleRepository.findByName(roleName)
                .orElse(null);
        if (role == null){
            return roleRepository.save(
                    Role.builder()
                            .name(roleName)
                            .build()
            );
        }
        return role;
    }


    // POST ADD USER MODAL ADMIN USER : ANGULAR
    @Override
    public Integer registerUnitUserByAdmin(UserDto userDto) {

        validator.validate(userDto);
        User user = UserDto.toEntity(userDto);

        Role role = roleRepository.findFirstById(2);
        user.addRoleToListRoleUser(role);

        UUID uuid = UUID.randomUUID();
        String uidRandGen = uuid.toString().substring(0,12);
        user.setPassword(passwordEncoder.encode("INITPASS" + uidRandGen));
        user.setActive(true);

        if (userDto.getGroupe() != null){
            Groupe groupe = groupeRepository.findFirstByName(userDto.getGroupe());
            groupe.addUserToListUser(user);
        }
        if (userDto.getChannel() != null){
            Channel channel = channelRepository.findFirstByName(userDto.getChannel());
            channel.addUserToListMemberUser(user);
        }

        emailService.sendConfirmationEmail(
                "INITPASS" + uidRandGen,
                user.getFirstname(),
                user.getLastname(),
                user.getEmail()
        );

        return userRepository.save(user).getId();
    }

    @Override
    public Integer modifyUserInfoByAdmin(UserDto userDto) {
        User u = userRepository.findById(userDto.getId()).get();
        u.setEmail(userDto.getEmail());
        u.setFirstname(userDto.getFirstname());
        u.setLastname(userDto.getLastname());

        return userRepository.save(u).getId();
    }

    @Override
    public Integer registerListUserByAdminNoGroupe(List<User> userDtoList) {

        try{
            // TODO: Integer + validator + return set violation pour le front (unicité, nom, attibruts ...)
            Role role = roleRepository.findFirstById(2);

            // Add To Database
            // TODO : Transaction ? , rollback on complete or partial error  ?
            for(var user: userDtoList){
                User u = new User();
                u.setEmail(user.getEmail());
                u.setFirstname(user.getFirstname());
                u.setLastname(user.getLastname());

            // Generate Password & Details
                UUID uuid = UUID.randomUUID();
                String uidRandGen = uuid.toString().substring(0,12);
                u.setPassword(passwordEncoder.encode("INITPASS" + uidRandGen));
                u.setActive(true);
                u.addRoleToListRoleUser(role);

            // Save
                userRepository.save(u);

            // Send Mail To All New User
                emailService.sendConfirmationEmail(
                        "INITPASS" + uidRandGen,
                        user.getFirstname(),
                        user.getLastname(),
                        user.getEmail()
                );
            }
        } catch (Exception e){
            return -1;
        }
        return 1;
    }
}
