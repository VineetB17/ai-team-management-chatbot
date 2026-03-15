package com.teamchatbot.backend.service;

import com.teamchatbot.backend.entity.Team;
import com.teamchatbot.backend.exception.ResourceNotFoundException;
import com.teamchatbot.backend.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team createTeam(String teamName) {
        Team team = new Team(teamName);
        return teamRepository.save(team);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team updateTeam(Long id, String newName) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));


        team.setTeamName(newName);
        return teamRepository.save(team);
    }

    public Team deactivateTeam(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));


        team.setIsActive(false);
        return teamRepository.save(team);
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id :" + id + " not found"));
    }



}
