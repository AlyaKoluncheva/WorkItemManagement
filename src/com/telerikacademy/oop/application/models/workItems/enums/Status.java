package com.telerikacademy.oop.application.models.workItems.enums;

public enum Status {
    NotDone, InProgress, Done, Active, Fixed, New, Unscheduled, Scheduled;

    @Override
    public String toString() {
        return switch (this) {
            case NotDone -> "Not Done";
            case InProgress -> "In Progress";
            case Done -> "Done";
            case Active -> "Active";
            case Fixed -> "Fixed";
            case New -> "New";
            case Unscheduled -> "Unscheduled";
            case Scheduled -> "Scheduled";
        };
    }
}

