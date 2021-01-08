package com.telerikacademy.oop.application.models.workItems.helperClasses;

import java.util.ArrayList;
import java.util.UUID;

public final class IdHandler {

    public static ArrayList<String> idList;

    static {
        idList = new ArrayList<>();
    }

    /**
     * Returns an randomly generated ID, and checks it if its unique in
     * program
     *
     * @return random 32-char long random unique ID
     */

    public static String getNewId() {
        String newId = "";
        while (true) {
            newId = UUID.randomUUID().toString();
            // if doesn't exist already , add to list, else empty String
            if (!idList.contains(newId)) {
                idList.add(newId);
                break;
            }
        }
        return newId;
    }
}
