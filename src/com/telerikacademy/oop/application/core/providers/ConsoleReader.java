package com.telerikacademy.oop.application.core.providers;

import com.telerikacademy.oop.application.core.contracts.Reader;

import java.util.Scanner;

public class ConsoleReader implements Reader {

    private final Scanner scanner;

    public ConsoleReader() {
        scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine();
    }
}