package com.telerikacademy.oop.application.models.workItems.enums;

public enum Status {
    NotDone, InProgress, Done, Active, Fixed, New, Unscheduled, Scheduled;

    @Override
    public String toString() {
        switch (this) {
            case NotDone:
                return "Not Done";
            case InProgress:
                return "In Progress";
            case Done:
                return "Done";
            case Active:
                return "Active";
            case Fixed:
                return "Fixed";
            case New:
                return "New";
            case Unscheduled:
                return "Unscheduled";
            case Scheduled:
                return "Scheduled";
            default:
                return "Unknown";
        }
    }
}

