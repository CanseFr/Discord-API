package com.canse.discord.repository;

import com.canse.discord.dto.UserDto;
import com.canse.discord.models.Groupe;
import com.canse.discord.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupeRepository extends JpaRepository<Groupe,Integer> {

    Groupe findFirstByName(String string);

    @Query(value = "SELECT discord_db.groupe.id, discord_db.groupe.name\n" +
            "FROM discord_db.groupe\n" +
            "    INNER JOIN groupe_user\n" +
            "        on groupe.id = groupe_user.groupe_id\n" +
            "    INNER JOIN discord_db.user\n" +
            "        ON groupe_user.user_id = user.id\n" +
            "    INNER JOIN member_user\n" +
            "        ON user.id = member_user.user_id\n" +
            "   INNER JOIN channel\n" +
            "       ON channel.id = member_user.channel_id\n" +
            "WHERE discord_db.channel.id =:idChannel\n" +
            "GROUP BY discord_db.groupe.name;",nativeQuery = true)
    List<Groupe> findAllGroupeContainInChannelByIdChannel(Integer idChannel);



}
