package com.telerikacademy.oop.application.models.workItems.enums;

public enum Priority {
    High,
    Medium,
    Low;

    @Override
    public String toString() {
        switch (this) {
            case High:
                return "High";
            case Medium:
                return "Medium";
            case Low:
                return "Low";
            default:
                return "Unknown";
        }
    }
}
