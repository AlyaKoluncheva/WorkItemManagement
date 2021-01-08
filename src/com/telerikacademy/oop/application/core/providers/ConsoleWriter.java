package com.telerikacademy.oop.application.core.providers;

import com.telerikacademy.oop.application.core.contracts.Writer;

public class ConsoleWriter implements Writer {

    public void write(String message) {
        System.out.print(message);
    }

    public void writeLine(String message) {
        System.out.println(message);
    }
}