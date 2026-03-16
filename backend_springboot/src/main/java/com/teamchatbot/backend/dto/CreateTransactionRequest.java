package com.teamchatbot.backend.dto;

import java.math.BigDecimal;

public class CreateTransactionRequest {

    private Long userId;
    private BigDecimal dataUsedMb;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getDataUsedMb() {
        return dataUsedMb;
    }

    public void setDataUsedMb(BigDecimal dataUsedMb) {
        this.dataUsedMb = dataUsedMb;
    }
}
