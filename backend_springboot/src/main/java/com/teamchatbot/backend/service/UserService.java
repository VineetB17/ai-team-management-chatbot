package com.teamchatbot.backend.service;

import com.teamchatbot.backend.dto.CreateUserRequest;
import com.teamchatbot.backend.entity.User;
import com.teamchatbot.backend.entity.Team;
import com.teamchatbot.backend.repository.UserRepository;
import com.teamchatbot.backend.repository.TeamRepository;
import com.teamchatbot.backend.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.teamchatbot.backend.dto.UpdateUserRequest;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    public UserService(UserRepository userRepository, TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    public User createUser(CreateUserRequest request) {

        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Team not found with id: " + request.getTeamId()));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setTeam(team);
        user.setIsActive(true);

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

    public User transferUser(Long userId, Long newTeamId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

        Team team = teamRepository.findById(newTeamId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Team not found with id: " + newTeamId));

        user.setTeam(team);

        return userRepository.save(user);
    }

    public Team getTeamOfUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

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


}
