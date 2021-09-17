package com.eh00.enums;

public enum UserRole {
    ADMIN("admin", "管理员"),
    USER("user", "用户");

    private String value;
    private String message;

    UserRole(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
