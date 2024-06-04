package com.team13.fantree.entity;

public enum UserStatusEnum {
    USER(Status.USER),
    NONEUSER(Status.NONEUSER);

    private final String status;

    UserStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public static class Status {
        public static final String USER = "USER";
        public static final String NONEUSER = "NONEUSER";
    }
}