package com.canse.discord.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean seen;
    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime seenAt;

//_______________________________________________________________________________________________________
//                                               RELATION
//_______________________________________________________________________________________________________

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private Channel channel;

    // Probleme relation avec heritage a regler

//    @OneToOne(targetEntity = Subject.class)
//    private Subject subject;

//_______________________________________________________________________________________________________
//                                               METHODE
//_______________________________________________________________________________________________________
}
