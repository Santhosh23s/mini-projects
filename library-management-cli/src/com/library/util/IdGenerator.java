package com.library.util;

public class IdGenerator {

    public static String generateBookId() {
        return "B"+System.currentTimeMillis();
    }

    public static String generateMemberId() {
        return "M"+System.currentTimeMillis();
    }

    public static String generateTransactionId() {
        return "T"+System.currentTimeMillis();
    }
}
