package com.canse.discord.models;

import com.canse.discord.enums.EVisibility;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    //@Enumerated(EnumType.STRING) Ancienement EVisibility
    private String visibility;

//_______________________________________________________________________________________________________
//                                               RELATION
//_______________________________________________________________________________________________________

    @OneToMany(mappedBy = "channel", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Message> messages;

    @OneToMany(mappedBy = "channel", fetch = FetchType.LAZY)
    private List<Meeting> meetings;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable( name = "subscribe_user",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> subscribe_user;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable( name = "member_user",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> member_user;

//_______________________________________________________________________________________________________
//                                               METHODE
//_______________________________________________________________________________________________________


    public void addMessageToList(Message message){
        this.messages.add(message);
    }

    public void addUserToListMemberUser(User user){
        this.member_user.add(user);
    }

    public void removeUserToListMemberUser(User user){
        this.member_user.remove(user);
    }



}
