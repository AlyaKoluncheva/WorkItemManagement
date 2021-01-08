package com.telerikacademy.oop.application.models.workItems.enums;

public enum Priority {
    High,
    Medium,
    Low;

    @Override
    public String toString() {
        return switch (this) {
            case High -> "High";
            case Medium -> "Medium";
            case Low -> "Low";
        };
    }
}
