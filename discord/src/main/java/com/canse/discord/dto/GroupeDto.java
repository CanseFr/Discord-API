package com.canse.discord.dto;

import com.canse.discord.models.Groupe;
import com.canse.discord.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GroupeDto {

    private Integer id;
    @NotNull(message = "Le name ne peut pas etre null")
    @NotEmpty(message = "Le name ne peut pas etre vide")
    @NotBlank(message = "Le name ne peut pas contenir d'éspace")
    private String name;

    private List<User> user;

    // Add Info
    private Integer idUserToAdd;
    private Integer idUserToDelete;

    public static GroupeDto fromEntity(Groupe groupe){
        return GroupeDto.builder()
                .id(groupe.getId())
                .name(groupe.getName())
                .user(groupe.getUser())
                .build();
    }

    public static GroupeDto fromEntityFinest(Groupe groupe){
        return GroupeDto.builder()
                .id(groupe.getId())
                .name(groupe.getName())
                .build();
    }

    public static Groupe toEntity(GroupeDto groupe){
        return Groupe.builder()
                .id(groupe.getId())
                .name(groupe.getName())
                .user(groupe.getUser())
                .build();
    }

}
