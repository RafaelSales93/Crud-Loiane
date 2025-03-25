package com.rafael.crud_spring.enums;

public enum category {  
    BACK_END("Back-End"), FRONT_END("Front-End");

    private String value;

    private category(String value) {
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
