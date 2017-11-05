package com.wallet.menu.impl;

import com.wallet.exceptions.WalletException;
import com.wallet.menu.Menu;

import java.io.IOException;

import static com.wallet.utils.ConsoleUtils.println;
import static com.wallet.utils.ConsoleUtils.readLine;

public class SubMenuExample extends Menu {

    private final static String PRINT_HELLO_WORLD = "1";

    public SubMenuExample(Menu previousMenu) {
        super(previousMenu);
    }

    @Override
    public void printMenu() throws IOException, WalletException {
        println("--------------------------------------------------------");
        println("------------------------SUBMENU-------------------------");
        println("-- 1. print Hola mundo from submenu");
        println("-- " + PREVIOUS + ". previous");
        println("-- " + EXIT + ". exit");
        println("--------------------------------------------------------");
        String input = readLine();
        handleInput(input);
    }

    @Override
    protected void handleInput(String input) throws IOException, WalletException {
        switch (input) {
            case PRINT_HELLO_WORLD:
                printHelloWorld();
                break;
            case PREVIOUS:
                printPreviousMenu();
                break;
            case EXIT:
                exit(NORMAL_EXIT_STATUTS);
                break;
            default:
                printNotValidOption();
        }
    }

    private void printHelloWorld() throws IOException, WalletException {
        println("Hello World from submenu!!!");
        printMenu();
    }
}
