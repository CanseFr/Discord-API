package com.canse.discord.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Enumerated(EnumType.STRING)
//    private ERole name;
    @Getter
    private String name;

//_______________________________________________________________________________________________________
//                                               METHODE
//_______________________________________________________________________________________________________
}
