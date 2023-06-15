package com.canse.discord.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@RequiredArgsConstructor
@MappedSuperclass
public abstract class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //@CreatedDate
    @Column(
            //nullable = false,
            updatable = false
    )
    private LocalDateTime sentAt;

//_______________________________________________________________________________________________________
//                                               RELATION
//_______________________________________________________________________________________________________

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "channel_id")
//    @JoinColumn(name = "channel_id", nullable = false)
    protected Channel channel;

//_______________________________________________________________________________________________________
//                                               METHODE
//_______________________________________________________________________________________________________
}
