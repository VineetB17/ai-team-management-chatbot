package com.teamchatbot.backend.service;

import com.teamchatbot.backend.dto.CreateTransactionRequest;
import com.teamchatbot.backend.dto.TopTeamDTO;
import com.teamchatbot.backend.dto.TopUserDTO;
import com.teamchatbot.backend.entity.TransactionData;
import com.teamchatbot.backend.entity.User;
import com.teamchatbot.backend.exception.UserInactiveException;
import com.teamchatbot.backend.repository.TransactionDataRepository;
import com.teamchatbot.backend.repository.UserRepository;
import com.teamchatbot.backend.exception.ResourceNotFoundException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionDataRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionDataRepository transactionRepository,
                              UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public TransactionData recordTransaction(CreateTransactionRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (!user.getIsActive()) {
            throw new UserInactiveException("Inactive users cannot generate transactions");

        }

        if (request.getDataUsedMb().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Data usage must be positive");
        }



        TransactionData transaction = new TransactionData();

        transaction.setUser(user);
        transaction.setTeam(user.getTeam());
        transaction.setDataUsedMb(request.getDataUsedMb());

        return transactionRepository.save(transaction);
    }

    public List<TransactionData> getTransactionsByUser(Long userId) {

        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return transactionRepository.findByUserUserId(userId);
    }

    public List<TransactionData> getTransactionsByTeam(Long teamId) {

        return transactionRepository.findByTeamTeamId(teamId);
    }


    public Double getTotalDataByUser(Long userId) {

        Double total = transactionRepository.getTotalDataByUser(userId);

        return total != null ? total : 0.0;
    }

    public Double getTotalDataByTeam(Long teamId) {

        Double total = transactionRepository.getTotalDataByTeam(teamId);

        return total != null ? total : 0.0;
    }

    public List<TopUserDTO> getTopUsers(int limit) {

        Pageable pageable = PageRequest.of(0, limit);

        return transactionRepository.findTopUsers(pageable);
    }

    public List<TopTeamDTO> getTopTeams(int limit) {

        Pageable pageable = PageRequest.of(0, limit);

        return transactionRepository.findTopTeams(pageable);
    }

    public List<TransactionData> getAllTransactions() {
        return transactionRepository.findAll();
    }





}
