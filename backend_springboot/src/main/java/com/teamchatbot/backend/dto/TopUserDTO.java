package com.teamchatbot.backend.dto;

public class TopUserDTO {

    private Long userId;
    private String username;
    private Double totalDataUsedMb;

    public TopUserDTO() {
    }


    public TopUserDTO(Long userId, String username, Number totalDataUsedMb) {
        this.userId = userId;
        this.username = username;
        this.totalDataUsedMb = totalDataUsedMb.doubleValue();
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Double getTotalDataUsedMb() {
        return totalDataUsedMb;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTotalDataUsedMb(Double totalDataUsedMb) {
        this.totalDataUsedMb = totalDataUsedMb;
    }
}
