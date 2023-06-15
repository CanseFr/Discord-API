package com.canse.discord.repository;

import com.canse.discord.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<UserDetails> findByEmail(String email);
    User findFirstById(Integer id);

    @Query(value ="SELECT user.id, user.active, user.email, user.firstname, user.lastname, user.password " +
            "FROM groupe " +
            "INNER JOIN groupe_user " +
            "ON groupe_user.groupe_id = groupe.id " +
            "INNER JOIN   user " +
            "ON groupe_user.user_id = user.id " +
            "WHERE groupe.id =:idGroupe" ,nativeQuery = true)
    List<User> findAllUserByGroupeId(Integer idGroupe);

    @Query(value ="SELECT user.id, user.active, user.email, user.firstname, user.lastname, user.password " +
            "FROM groupe " +
            "INNER JOIN groupe_user " +
            "ON groupe_user.groupe_id = groupe.id " +
            "INNER JOIN   user " +
            "ON groupe_user.user_id = user.id " +
            "WHERE groupe.name =:groupe", nativeQuery = true)
    List<User> findAllUserByGourpeName( String groupe);

    @Query(value = "SELECT discord_db.user.id,user.active, discord_db.user.email, discord_db.user.firstname, discord_db.user.lastname, discord_db.user.password  " +
            "FROM discord_db.user " +
            "INNER JOIN discord_db.member_user " +
            "ON discord_db.member_user.user_id = discord_db.user.id " +
            "INNER JOIN discord_db.channel " +
            "ON discord_db.channel.id = member_user.channel_id " +
            "WHERE discord_db.channel.id=:idChannel", nativeQuery = true)
    List<User> findAllUserByChannelId(Integer idChannel);

}
