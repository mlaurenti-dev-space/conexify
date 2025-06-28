package com.devspace.conexfy.models;

public final class DevEncryptedString {
    private final String value;

    public DevEncryptedString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
