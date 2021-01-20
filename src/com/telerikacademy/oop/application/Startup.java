package com.telerikacademy.oop.application;

import com.telerikacademy.oop.application.core.ApplicationEngineImpl;

public class Startup {

    public static void main(String[] args) {
        ApplicationEngineImpl engine = new ApplicationEngineImpl();
        engine.start();
    }

}