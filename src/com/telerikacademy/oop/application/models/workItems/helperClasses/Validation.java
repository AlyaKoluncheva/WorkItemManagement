package com.telerikacademy.oop.application.models.workItems.helperClasses;

public class Validation {

    /**
     * Validates if the object is null
     *
     * @param object the object we want to test
     * @throws IllegalArgumentException if the object is null
     */

    public static void validateNotNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Validates if the object is null
     *
     * @param value   the value to check
     * @param min     minimal value
     * @param max     maximum value
     * @param message message to throw if the value isn't between the min and max
     * @throws IllegalArgumentException message
     */
    public static void validateValue(String value, int max, int min, String message) {
        if (value.length() < min || value.length() > max) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Validates if the object is null
     *
     * @param value        the value to check
     * @param min          minimal value
     * @param errorMessage message to throw if the value is below the min
     * @throws IllegalArgumentException message
     */

    public static void validateMin(double value, double min, String errorMessage) {
        if (value < min) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Validates if the object is null
     *
     * @param value        the value to check
     * @param min          minimal value
     * @param max          maximum value
     * @param errorMessage message to throw if the value is below the min
     * @throws IllegalArgumentException message
     */

    public static void validateValueInRange(double value, double min, double max, String errorMessage) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
