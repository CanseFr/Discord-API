package com.canse.discord.repository;

import com.canse.discord.models.Message;
import com.canse.discord.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

//    @Query(value ="select * from message where channel_id = (select channel.id from channel where channel.name =:channel)", nativeQuery = true)
//    List<Hello> findAllMessageByIdChannelByStringChannel(String channel);

    List<Message> findMessagesByChannel_Name(String channelName);
    List<Message> findMessagesByChannel_Id(Integer idchannel);


//    @Modifying
    @Query(value = "UPDATE discord_db.message SET channel_id =:idChannel WHERE id=:idMessageToUpdate", nativeQuery = true)
    void updateChannelIdOfMessage(Integer idMessageToUpdate,Integer idChannel);

}
