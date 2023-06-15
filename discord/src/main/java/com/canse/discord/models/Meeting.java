package com.canse.discord.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Meeting extends Subject {

    private String name;
    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime dateTime;
    private Integer duration;

//_______________________________________________________________________________________________________
//                                               RELATION
//_______________________________________________________________________________________________________

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Channel channel;

//_______________________________________________________________________________________________________
//                                               METHODE
//_______________________________________________________________________________________________________
}
