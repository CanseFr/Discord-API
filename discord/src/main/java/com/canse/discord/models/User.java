package com.canse.discord.models;

import com.canse.discord.enums.ERole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String email;
    private String password;
    private String lastname;
    private String firstname;
    private boolean active;

//_______________________________________________________________________________________________________
//                                               RELATION
//_______________________________________________________________________________________________________
    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinTable( name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Notification> notificationList;

//    @ManyToMany(mappedBy = "subscribe_user", cascade = CascadeType.ALL)
//    private List<Channel> subscribeToChannel;

//    @ManyToMany(mappedBy = "member_user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Channel> memberToChannel;

//_______________________________________________________________________________________________________
//                                               Method
//_______________________________________________________________________________________________________

    public void addRoleToListRoleUser(Role role){
            this.roles = new ArrayList<>();
            this.roles.add(role);
    }

//_______________________________________________________________________________________________________
//                                               USER DETAILS
//_______________________________________________________________________________________________________

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(roles.get(0).getName()));
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return active;
    }
}
