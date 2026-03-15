package com.teamchatbot.backend.controller;

import com.teamchatbot.backend.dto.CreateTeamRequest;
import com.teamchatbot.backend.entity.Team;
import com.teamchatbot.backend.entity.User;
import com.teamchatbot.backend.service.TeamService;
import com.teamchatbot.backend.service.UserService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    private final UserService userService;

    public TeamController(TeamService teamService, UserService userService) {
        this.teamService = teamService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public Team createTeam(@RequestBody CreateTeamRequest request) {
        return teamService.createTeam(request);
    }


    @GetMapping("/all")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PutMapping("/{id}")
    public Team updateTeam(@PathVariable Long id, @RequestBody Team team) {
        return teamService.updateTeam(id, team.getTeamName());
    }


    @PutMapping("/deactivate/{id}")
    public Team deactivateTeam(@PathVariable Long id) {
        return teamService.deactivateTeam(id);
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @GetMapping("/{id}/users")
    public List<User> getUsersByTeam(@PathVariable Long id) {
        return userService.getUsersByTeamId(id);
    }



}
