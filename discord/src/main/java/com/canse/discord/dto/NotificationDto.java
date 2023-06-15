package com.canse.discord.dto;

import com.canse.discord.models.Channel;
import com.canse.discord.models.Notification;
import com.canse.discord.models.Subject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class NotificationDto {

    private Integer id;
    @NotNull(message = "Seen ne peut pas etre null")
    @NotEmpty(message = "Seen ne peut pas etre vide")
    @NotBlank(message = "Seen ne peut pas contenir d'éspace")
    private Boolean seen; // a voire ?
    @PastOrPresent
    private LocalDateTime seenAt;
    private Channel channel;
    private Subject subject; // Definir le role de ce champ, regler probleme relation du model

    public static NotificationDto fromEntity(Notification notification){
        return NotificationDto.builder()
                .id(notification.getId())
                .seen(notification.getSeen())
                .seenAt(notification.getSeenAt())
                .channel(notification.getChannel())
                //.subject(notification.getSubject())
                .build();
    }
    public static Notification toEntity(NotificationDto notification){
        return Notification.builder()
                .id(notification.getId())
                .seen(notification.getSeen())
                .seenAt(notification.getSeenAt())
                .channel(notification.getChannel())
                //.subject(notification.getSubject())
                .build();
    }
}
