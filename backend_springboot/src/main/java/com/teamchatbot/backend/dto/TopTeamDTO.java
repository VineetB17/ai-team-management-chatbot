package com.teamchatbot.backend.dto;

public class TopTeamDTO {

    private Long teamId;
    private String teamName;
    private Double totalDataUsedMb;

    public TopTeamDTO() {}


    public TopTeamDTO(Long teamId, String teamName, Double totalDataUsedMb) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.totalDataUsedMb = totalDataUsedMb;
    }


    public Long getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public Double getTotalDataUsedMb() {
        return totalDataUsedMb;
    }


    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTotalDataUsedMb(Double totalDataUsedMb) {
        this.totalDataUsedMb = totalDataUsedMb;
    }
}
