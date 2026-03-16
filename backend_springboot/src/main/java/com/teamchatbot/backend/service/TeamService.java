package com.teamchatbot.backend.service;

import com.teamchatbot.backend.dto.CreateTeamRequest;
import com.teamchatbot.backend.entity.Team;
import com.teamchatbot.backend.entity.User;
import com.teamchatbot.backend.exception.ResourceNotFoundException;
import com.teamchatbot.backend.exception.TeamOperationException;
import com.teamchatbot.backend.repository.TeamRepository;
import com.teamchatbot.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public TeamService(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Team createTeam(CreateTeamRequest request) {

        Team team = new Team();
        team.setTeamName(request.getTeamName());
        team.setIsActive(true);

        Team savedTeam = teamRepository.save(team);

        User user = new User();
        user.setUsername(request.getUsername());
        user.setIsActive(true);
        user.setTeam(savedTeam);

        userRepository.save(user);

        return savedTeam;
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

    public Team deactivateTeam(Long teamId) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Team not found"));

        long userCount = userRepository.countByTeamTeamId(teamId);

        if (userCount > 0) {
            throw new TeamOperationException("Team cannot be deactivated while users are assigned");
        }

        team.setIsActive(false);

        return teamRepository.save(team);
    }



    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id :" + id + " not found"));
    }

    @Transactional
    public void deleteTeam(Long teamId) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Team not found"));

        List<User> users = userRepository.findByTeamTeamId(teamId);

        for (User user : users) {
            user.setTeam(null);
            userRepository.save(user);
        }

        teamRepository.delete(team);
    }





}
