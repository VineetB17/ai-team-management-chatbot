package com.teamchatbot.backend.repository;

import com.teamchatbot.backend.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
