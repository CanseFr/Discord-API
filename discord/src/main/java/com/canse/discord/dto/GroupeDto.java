package com.canse.discord.dto;

import com.canse.discord.models.Groupe;
import com.canse.discord.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupeDto {

    private Integer id;
    @NotNull(message = "Le name ne peut pas etre null")
    @NotEmpty(message = "Le name ne peut pas etre vide")
    @NotBlank(message = "Le name ne peut pas contenir d'éspace")
    @Size(min = 2, message = "Le nom du groupe doit contenir un minimum de 2 characteres")
    @Size(max = 8, message = "Le nom du groupe doit contenir un maximum de 8 characteres")
    private String name;
    private List<User> user;

    // Info Objet Service Front
    private Integer idUserToAdd;
    private Integer idUserToDelete;

    //__________________________________________________________________________________________________________________
    //                                                   SETTER : Format Input
    //__________________________________________________________________________________________________________________

    public void setName(String name) {
        String trimedString = name.trim().toLowerCase();
        this.name = trimedString.trim().substring(0, 1).toUpperCase() + trimedString.substring(1);
    }


    //__________________________________________________________________________________________________________________
    //                                                   METHODE
    //__________________________________________________________________________________________________________________

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
