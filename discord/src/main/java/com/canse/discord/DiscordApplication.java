package com.canse.discord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class DiscordApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(DiscordApplication.class, args);
		System.out.println("->OK<-");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DiscordApplication.class);
	}
}
