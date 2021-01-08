package com.telerikacademy.oop.application.models.workItems.enums;

public enum Size {
    Large, Medium, Small;

    @Override
    public String toString() {
        switch (this) {
            case Large:
                return "Large";
            case Medium:
                return "Medium";
            case Small:
                return "Small";
            default:
                return "Unknown";
        }

    }
}
