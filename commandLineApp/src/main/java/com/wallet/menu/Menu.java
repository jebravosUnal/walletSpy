package com.wallet.menu;

import com.wallet.exceptions.WalletException;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class Menu {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RESET = "\u001B[0m";
    protected BufferedReader br = null;

    protected final static String EXIT = "q";
    protected final static String PREVIOUS = "p";
    protected static final int NORMAL_EXIT_STATUTS = 0;

    public abstract void printMenu() throws IOException, WalletException;

    protected abstract void handleInput(String input) throws IOException, WalletException;

    protected void printNotValidOption() throws IOException, WalletException {
        println("Not a valid option!! Select another one");
        printMenu();
    }
    public void println(String line){
        println(line, "");
    }

    public void println(String line, String color){
        System.out.println(color + line + ANSI_RESET);
    }

    public void exit(int status){
        System.exit(status);
    }

}
