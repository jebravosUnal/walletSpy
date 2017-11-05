package com.wallet.menu;

import com.wallet.exceptions.WalletException;

import java.io.BufferedReader;
import java.io.IOException;

import static com.wallet.utils.ConsoleUtils.println;

public abstract class Menu {

    private Menu previousMenu;

    protected final static String EXIT = "q";
    protected final static String PREVIOUS = "p";
    protected static final int NORMAL_EXIT_STATUTS = 0;

    protected Menu(Menu previousMenu) {
        this.previousMenu = previousMenu;
    }

    protected Menu(){}

    public abstract void printMenu() throws IOException, WalletException;

    protected abstract void handleInput(String input) throws IOException, WalletException;

    protected void printNotValidOption() throws IOException, WalletException {
        println("Not a valid option!! Select another one");
        printMenu();
    }

    protected void printPreviousMenu() throws IOException, WalletException {
        previousMenu.printMenu();
    }

    public void exit(int status){
        System.exit(status);
    }

}
