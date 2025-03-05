package org.example.currency.utils;

import lombok.Getter;

@Getter
public enum UserRole {
    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN"),
    MANAGER("MANAGER");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }
}
