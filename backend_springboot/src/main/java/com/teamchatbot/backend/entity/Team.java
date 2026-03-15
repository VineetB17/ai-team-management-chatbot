package com.teamchatbot.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "team_ref")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column(nullable = false)
    private String teamName;

    private Boolean isActive = true;

    // Constructors
    public Team() {}

    public Team(String teamName) {
        this.teamName = teamName;
        this.isActive = true;
    }

    // Getters and Setters
    public Long getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
