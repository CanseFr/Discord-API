package com.canse.discord.models;

import com.canse.discord.dto.UserDto;
import com.canse.discord.repository.UserRepository;
import com.canse.discord.services.UserService;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

//_______________________________________________________________________________________________________
//                                               RELATION
//_______________________________________________________________________________________________________
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable( name = "groupe_user",
            joinColumns = @JoinColumn(name = "groupe_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @ToString.Exclude
    private List<User> user;

//_______________________________________________________________________________________________________
//                                               METHODE
//_______________________________________________________________________________________________________

    public void addUserToListUser(User user){
        this.user.add(user);
    }
    public void deleteUserToListUser(User user){
        this.user.remove(user);
    }

}
