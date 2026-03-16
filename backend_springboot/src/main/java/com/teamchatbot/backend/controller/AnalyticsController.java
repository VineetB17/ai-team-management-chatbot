package com.teamchatbot.backend.controller;

import com.teamchatbot.backend.dto.TopTeamDTO;
import com.teamchatbot.backend.dto.TopUserDTO;
import com.teamchatbot.backend.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final TransactionService transactionService;

    public AnalyticsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/top-users")
    public List<TopUserDTO> getTopUsers(
            @RequestParam(defaultValue = "5") int limit) {

        return transactionService.getTopUsers(limit);
    }

    @GetMapping("/top-teams")
    public List<TopTeamDTO> getTopTeams(
            @RequestParam(defaultValue = "5") int limit) {

        return transactionService.getTopTeams(limit);
    }

}
