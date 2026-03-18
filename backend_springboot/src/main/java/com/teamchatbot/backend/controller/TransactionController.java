package com.teamchatbot.backend.controller;

import com.teamchatbot.backend.dto.CreateTransactionRequest;
import com.teamchatbot.backend.entity.TransactionData;
import com.teamchatbot.backend.service.TransactionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public TransactionData createTransaction(@RequestBody CreateTransactionRequest request) {
        return transactionService.recordTransaction(request);
    }

    @GetMapping("/users/{userId}")
    public List<TransactionData> getUserTransactions(@PathVariable Long userId) {
        return transactionService.getTransactionsByUser(userId);
    }


    @GetMapping("/teams/{teamId}")
    public List<TransactionData> getTeamTransactions(@PathVariable Long teamId) {
        return transactionService.getTransactionsByTeam(teamId);
    }

    @GetMapping("/user/{userId}/total-data")
    public Double getTotalDataByUser(@PathVariable Long userId) {
        return transactionService.getTotalDataByUser(userId);
    }

    @GetMapping("/team/{teamId}/total-data")
    public Double getTotalDataByTeam(@PathVariable Long teamId) {
        return transactionService.getTotalDataByTeam(teamId);
    }


    @GetMapping
    public ResponseEntity<List<TransactionData>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }




}
