package com.canse.discord.repository;

import com.canse.discord.models.Channel;
import com.canse.discord.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel,Integer> {

    Channel findChannelById(Integer id);

    Channel findFirstByName(String string);





}
