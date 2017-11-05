package com.wallet.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by EBR3556 on 05/11/2017.
 */
public class ConsoleUtils {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RESET = "\u001B[0m";

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void println(String line){
        println(line, "");
    }

    public static void println(String line, String color){
        System.out.println(color + line + ANSI_RESET);
    }

    public static String readLine() throws IOException {
        return br.readLine();
    }

}
