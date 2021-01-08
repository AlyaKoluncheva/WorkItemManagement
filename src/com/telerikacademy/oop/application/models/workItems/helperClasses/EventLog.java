package com.telerikacademy.oop.application.models.workItems.helperClasses;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventLog {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
    private final String description;
    private final LocalDateTime timestamp;

    public EventLog(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }

        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }


    /**
     * Returns history in the format: [dd-MMMM-yyyy HH:mm:ss] History
     *
     * @return String History
     */

    public String viewHistory() {
        return String.format("[%s] %s", timestamp.format(formatter), description);
    }


    /**
     * Returns comments in the format: [dd-MMMM-yyyy HH:mm:ss] Comment
     *
     * @return String Comments
     */

    public String viewComments() {
        return String.format("[%s] %s", timestamp.format(formatter), description);
    }
}
