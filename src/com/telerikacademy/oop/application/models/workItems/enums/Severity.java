package com.telerikacademy.oop.application.models.workItems.enums;

public enum Severity {
    Critical, Major, Minor;

    @Override
    public String toString() {
        return switch (this) {
            case Critical -> "Critical";
            case Major -> "Major";
            case Minor -> "Minor";
        };
    }
}
