package com.canse.discord;

import com.canse.discord.models.Groupe;
import com.canse.discord.models.User;
import com.canse.discord.repository.GroupeRepository;
import com.canse.discord.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SpringBootApplication
@EnableJpaAuditing
public class DiscordApplication  {
	public static void main(String[] args) {
		SpringApplication.run(DiscordApplication.class, args);
		System.out.println("->OK<-");
	}
}
