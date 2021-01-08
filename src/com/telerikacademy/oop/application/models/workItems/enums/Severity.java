package com.telerikacademy.oop.application.models.workItems.enums;

public enum Severity {
    Critical, Major, Minor;

    @Override
    public String toString() {
        switch (this) {
            case Critical:
                return "Critical";
            case Major:
                return "Major";
            case Minor:
                return "Minor";
            default:
                return "Unknown";
        }
    }
}
