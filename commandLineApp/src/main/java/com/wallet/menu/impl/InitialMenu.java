package com.wallet.menu.impl;

import com.wallet.exceptions.WalletException;
import com.wallet.menu.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.wallet.utils.ConsoleUtils.println;
import static com.wallet.utils.ConsoleUtils.readLine;

public class InitialMenu extends Menu {
    private final static String PRINT_HELLO_WORLD = "1";
    private final static String GO_TO_SUBMENU = "2";

//    private Map<String, String> options = new HashMap<>();

    public InitialMenu() {
//        br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void printMenu() throws IOException, WalletException {
        println("--------------------------------------------------------");
        println("---------------------------MENU-------------------------");
        println("-- 1. print Hola mundo");
        println("-- 2. subMenuExample");
        println("-- " + EXIT + ". exit");
        println("--------------------------------------------------------");
        String input = readLine();
        handleInput(input);
    }

    @Override
    public void handleInput(String input) throws IOException, WalletException {
        switch (input) {
            case PRINT_HELLO_WORLD:
                printHelloWorld();
                break;
            case GO_TO_SUBMENU:
                new SubMenuExample(this).printMenu();
                break;
            case EXIT:
                exit(NORMAL_EXIT_STATUTS);
            default:
                printNotValidOption();
        }
    }

    private void printHelloWorld() throws IOException, WalletException {
        println("Hello World!!!");
        printMenu();

    }


}
