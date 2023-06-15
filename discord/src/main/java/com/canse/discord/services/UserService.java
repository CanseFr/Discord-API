package com.canse.discord.services;

import com.canse.discord.dto.UserDto;
import com.canse.discord.dto.auth.AuthenticationRequest;
import com.canse.discord.dto.auth.AuthenticationResponse;
import com.canse.discord.models.User;

import java.util.List;

public interface UserService extends AbstractService<UserDto>{
    Integer validateAccount(Integer id);
    Integer invalidateAccount(Integer id);
    AuthenticationResponse register (UserDto user);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    List<User> findUserByGroupeName(String groupe);
    List<User> findAllUserByGroupeId(Integer idGroupe);
    Integer registerUnitUserByAdmin(UserDto user);
    Integer modifyUserInfoByAdmin(UserDto userDto);
    Integer registerListUserByAdminNoGroupe(List<User> userDtoList);
}