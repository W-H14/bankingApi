package com.thecoalition.bankingApi.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Type {
    SAVINGS("Savings"),
    CHECKING("Checking"),
    CREDIT("Credit");

    private final String type;

    Type(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }
}