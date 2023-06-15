package com.canse.discord.dto;

import com.canse.discord.models.Channel;
import com.canse.discord.models.File;
import com.canse.discord.models.Message;
import com.canse.discord.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MessageDto {
    // Herited By Subject
    private Integer id;
    private LocalDateTime sentAt;
    protected Channel channel;

    // Models Attributs
    @NotNull(message = "le Contenu du message ne peut pas etre vide !")
    private String content;
    private User user;
    private File file;

    // Add Information Attributs for Json -> Voire selon amelioration si encore necessaire

    private String userLastname; // -> Utile pour controlleur chat get MessageOf
    private Integer userId; // ! Recuperation objet front Json
    private Integer channelId; // ! Recuperation objet front Json

    // Creer un dto leger
    // Finest -> Utiliser ce Dto pour le controlleur chat get Hello Of [UserlastName & content] actuellement

    // Regular
    public static MessageDto fromEntity(Message message){
        return MessageDto.builder()
                .id(message.getId())
                .sentAt(message.getSentAt())
                .channel(message.getChannel())
                .content(message.getContent())
                .user(message.getUser())
                .file(message.getFile())
                .userLastname(message.getUser().getLastname())
                .build();
    }
    public static Message toEntity(MessageDto message){

        Message messageModels = new Message();
        messageModels.setContent(message.getContent());
        messageModels.setId(message.getId());
        messageModels.setUser(message.getUser());
        messageModels.setSentAt(message.getSentAt());
        messageModels.setChannel(message.getChannel());
        messageModels.setContent(message.getContent());
        messageModels.setFile(message.getFile());


        return messageModels;
    }
}
