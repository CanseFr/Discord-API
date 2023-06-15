package com.canse.discord.dto;

import com.canse.discord.models.Channel;
import com.canse.discord.models.Meeting;
import com.canse.discord.models.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class MeetingDto {

    // Herited By Subject
    private Integer id;
    private LocalDateTime sentAt;
    protected Channel channel;

    // Models Attributs
    @NotNull(message = "Le name ne peut pas etre null")
    @NotEmpty(message = "Le name ne peut pas etre vide")
    @NotBlank(message = "Le name ne peut pas contenir d'éspace")
    private String name;
    @Future
    private LocalDateTime dateTime;
    @Positive
    private Integer duration;
    private User user;


    public static MeetingDto fromEntity(Meeting meeting){
        return MeetingDto.builder()
                .id(meeting.getId())
                .channel(meeting.getChannel())
                .sentAt(meeting.getSentAt())
                .name(meeting.getName())
                .dateTime(meeting.getDateTime())
                .duration(meeting.getDuration())
                .user(meeting.getUser())
                .build();
    }

    public static Meeting toEntity(MeetingDto meeting){
        Meeting meetingModels = new Meeting();
        meetingModels.setSentAt(meeting.getSentAt());
        meetingModels.setName(meeting.getName()) ;
        meetingModels.setDateTime(meeting.getDateTime());
        meetingModels.setDuration(meeting.getDuration());
        meetingModels.setUser(meeting.getUser());
        meetingModels.setChannel(meeting.getChannel());

        return meetingModels;
    }

}
