package com.canse.discord.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Message extends Subject {

    private String content;

//_______________________________________________________________________________________________________
//                                               RELATION
//_______________________________________________________________________________________________________

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private File file;


//_______________________________________________________________________________________________________
//                                               METHODE
//_______________________________________________________________________________________________________
}
