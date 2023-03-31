package com.dku.mentoring.register.model.entity;

public enum RegisterStatus {
    /**
     * 진행중
     */
    PROGRESS("진행중"),

    /**
     * 완료
     */
    COMPLETE("완료");
    private String name;
    RegisterStatus(String name) {
        this.name = name;
    }
}
