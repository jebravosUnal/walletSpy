package com.wallet.menu;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class Menu {

    protected BufferedReader br = null;

    protected final static String EXIT = "q";
    protected final static String PREVIOUS = "p";
    protected static final int NORMAL_EXIT_STATUTS = 0;

    public abstract void printMenu() throws IOException;

    protected abstract void handleInput(String input) throws IOException;

    protected void printNotValidOption() throws IOException{
        System.out.println("Not a valid option!! Select another one");
        printMenu();
    }

    public void exit(int status){
        System.exit(status);
    }

}
