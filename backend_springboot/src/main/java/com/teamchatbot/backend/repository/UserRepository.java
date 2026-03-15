package com.teamchatbot.backend.repository;

import com.teamchatbot.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByTeamTeamId(Long teamId);
    long countByTeamTeamId(Long teamId);

}
