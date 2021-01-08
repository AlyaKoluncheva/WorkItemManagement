package com.telerikacademy.oop.application.models.workItems.enums;

public enum Size {
    Large, Medium, Small;

    @Override
    public String toString() {
        return switch (this) {
            case Large -> "Large";
            case Medium -> "Medium";
            case Small -> "Small";
        };

    }
}
