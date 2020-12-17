package com.example.demo.utils;

public class TestUtils {
    private static int COUNTER=0;
    public static void printTestInfo(String testName) {
        System.out.println(String.format("Developer Test : %d | Test: %s",++COUNTER,testName));
    }
}

