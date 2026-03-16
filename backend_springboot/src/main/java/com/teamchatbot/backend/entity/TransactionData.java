package com.teamchatbot.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_data")
public class TransactionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonIgnoreProperties({"users"})
    private Team team;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"team"})
    private User user;

    @Column(name = "data_used_mb", nullable = false)
    private BigDecimal dataUsedMb;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public TransactionData() {}

    @PrePersist
    public void setTimestamp() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getDataUsedMb() {
        return dataUsedMb;
    }

    public void setDataUsedMb(BigDecimal dataUsedMb) {
        this.dataUsedMb = dataUsedMb;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
