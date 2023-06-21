package com.canse.discord.dto;

import com.canse.discord.enums.ERole;
import com.canse.discord.models.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {
    private Integer id;
    @NotNull(message = "L'email ne peut pas etre null")
    @NotEmpty(message = "L'email ne peut pas etre vide")
    @NotBlank(message = "L'email ne peut pas contenir d'éspace")
    @Email(message = "l'email doit respecter le format suivant mail@exemple.com")
    private String email;
    @NotNull(message = "Le password ne peut pas etre null")
    @NotEmpty(message = "Le password ne peut pas etre vide")
    @NotBlank(message = "Le password ne peut pas contenir d'éspace")
    @Size(min = 8, max = 20)
    private String password;
    @NotNull(message = "Le lastname ne peut pas etre null")
    @NotEmpty(message = "Le lastname ne peut pas etre vide")
    @NotBlank(message = "Le lastname ne peut pas contenir d'éspace")
    private String lastname;
    @NotNull(message = "Le firstname ne peut pas etre null")
    @NotEmpty(message = "Le firstname ne peut pas etre vide")
    @NotBlank(message = "Le firstname ne peut pas contenir d'éspace")
    private String firstname;
    //@NotNull(message = "Le role ne peut pas etre null")
    //@NotEmpty(message = "Le role ne peut pas etre vide")
    private boolean active;
    private List<Role> roles;
    private List<Notification> notificationList;
    //private List<Channel> subscribeToChannel;
//    private List<Channel> memberToChannel;

// Json Front
    private String channel;
    private String groupe;


    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .lastname(user.getLastname())
                .firstname(user.getFirstname())
                .roles(user.getRoles())
                .notificationList(user.getNotificationList())
                .active(user.isActive())
                //.subscribeToChannel(user.getSubscribeToChannel())
                .build();
    }


    public static User toEntity(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .firstname(userDto.getFirstname().trim().toLowerCase())
                .lastname(userDto.getLastname().trim().toLowerCase())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
