package com.canse.discord.repository;

import com.canse.discord.models.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting,Integer> {
}
