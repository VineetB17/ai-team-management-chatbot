package com.teamchatbot.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonIgnoreProperties("users")

    private Team team;


    public User() {}

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Team getTeam() {
        return team;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setTeam(Team team) {
        this.team = team;
    }




    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
