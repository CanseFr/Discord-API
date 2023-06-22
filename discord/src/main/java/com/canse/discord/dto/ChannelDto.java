package com.canse.discord.dto;

import com.canse.discord.enums.EVisibility;
import com.canse.discord.models.*;
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
public class ChannelDto {

    private Integer id;
    @NotNull(message = "Le name ne peut pas etre null")
    @NotEmpty(message = "Le name ne peut pas etre vide")
    @NotBlank(message = "Le name ne peut pas contenir d'éspace")
    @Size(min = 2, message = "Le nom du groupe doit contenir un minimum de 2 characteres")
    @Size(max = 7, message = "Le nom du groupe doit contenir un maximum de 7 characteres")
    private String name;
    @NotNull(message = "La vidibilité du channel ne peut pas etre null")
    @NotEmpty(message = "La vidibilité du channel ne peut pas etre vide")
    @NotBlank(message = "La vidibilité du channel ne peut pas contenir d'éspace")
    private String visibility;

    private List<Message> messages;
    private List<Meeting> meetings;
    private List<User> subscribe_user;
    private List<User> member_user;

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

    public static ChannelDto fromEntity(Channel channel){
        return ChannelDto.builder()
                .id(channel.getId())
                .name(channel.getName())
                .visibility(channel.getVisibility())
                .messages(channel.getMessages())
                .member_user(channel.getMember_user())
                .build();
    }
    public static Channel toEntity(ChannelDto channel){
        return Channel.builder()
                .id(channel.getId())
                .name(channel.getName().trim())
                .visibility(channel.getVisibility())
                .messages(channel.getMessages())
//                .meetings(channel.getMeetings())
                .subscribe_user(channel.getSubscribe_user())
                .member_user(channel.getMember_user())
                .build();
    }

}
