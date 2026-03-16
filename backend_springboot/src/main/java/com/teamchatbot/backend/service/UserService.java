package com.teamchatbot.backend.service;

import com.teamchatbot.backend.dto.CreateUserRequest;
import com.teamchatbot.backend.entity.User;
import com.teamchatbot.backend.entity.Team;
import com.teamchatbot.backend.exception.BadRequestException;
import com.teamchatbot.backend.repository.UserRepository;
import com.teamchatbot.backend.repository.TeamRepository;
import com.teamchatbot.backend.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.teamchatbot.backend.dto.UpdateUserRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    public UserService(UserRepository userRepository, TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public User createUser(CreateUserRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setIsActive(true);

        if (request.getTeamId() != null) {

            Team team = teamRepository.findById(request.getTeamId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Team not found"));

            user.setTeam(team);

            if (!team.getIsActive()) {
                team.setIsActive(true);
                teamRepository.save(team);
            }
        }

        return userRepository.save(user);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public List<User> getUsersByTeamId(Long teamId) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Team not found with id: " + teamId));

        return userRepository.findByTeamTeamId(teamId);
    }

    @Transactional
    public User transferUser(Long userId, Long newTeamId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (user.getTeam().getTeamId().equals(newTeamId)) {
            throw new BadRequestException("User already belongs to this team");

        }


        Team oldTeam = user.getTeam();

        Team newTeam = teamRepository.findById(newTeamId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Team not found"));

        user.setTeam(newTeam);
        userRepository.save(user);

        if (oldTeam != null) {

            long remainingUsers = userRepository.countByTeamTeamId(oldTeam.getTeamId());

            if (remainingUsers == 0) {
                oldTeam.setIsActive(false);
                teamRepository.save(oldTeam);
            }
        }

        if (!newTeam.getIsActive()) {
            newTeam.setIsActive(true);
            teamRepository.save(newTeam);
        }

        return user;
    }




    public Team getTeamOfUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

        if (user.getTeam() == null) {
            throw new ResourceNotFoundException("User is not assigned to a team");
        }

        return user.getTeam();
    }

    public User updateUser(Long userId, UpdateUserRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }

        if (request.getIsActive() != null) {
            user.setIsActive(request.getIsActive());
        }

        return userRepository.save(user);
    }

    public User deactivateUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setIsActive(false);

        Team team = user.getTeam();

        if (team != null) {

            long activeUsers = userRepository.countByTeamTeamIdAndIsActiveTrue(team.getTeamId());

            if (activeUsers == 0) {
                team.setIsActive(false);
                teamRepository.save(team);
            }
        }


        return userRepository.save(user);
    }

    public User activateUser(Long userId) {


        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (user.getIsActive()) {
            throw new IllegalStateException("User is already active");
        }

        user.setIsActive(true);

        Team team = user.getTeam();



        if (team != null && !team.getIsActive()) {
            team.setIsActive(true);
            teamRepository.save(team);
        }

        return userRepository.save(user);
    }










}
