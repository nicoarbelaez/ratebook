package com.nicoarbelaez.ratebook.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsolePrinter {
    private static final String PREFIX = "[ratebook]"; 
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void info(String message) {
        System.out.println(formatMessage("INFO", message));
    }

    public static void warning(String message) {
        System.out.println(formatMessage("WARNING", message));
    }

    public static void error(String message) {
        System.out.println(formatMessage("ERROR", message));
    }

    private static String formatMessage(String level, String message) {
        return String.format("%s %s [%s] %s", PREFIX, LocalDateTime.now().format(DATE_FORMAT), level, message);
    }
}
