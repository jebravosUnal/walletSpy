package com.wallet.menu.impl;

import com.wallet.menu.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InitialMenu extends Menu {
    private final static String PRINT_HELLO_WORLD = "1";
    private final static String GO_TO_SUBMENU = "2";

//    private Map<String, String> options = new HashMap<>();

    public InitialMenu() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void printMenu() throws IOException {
        System.out.println("--------------------------------------------------------");
        System.out.println("---------------------------MENU-------------------------");
        System.out.println("-- 1. print Hola mundo");
        System.out.println("-- 2. subMenuExample");
        System.out.println("-- " + EXIT + ". exit");
        System.out.println("--------------------------------------------------------");
        String input = br.readLine();
        handleInput(input);
    }

    @Override
    public void handleInput(String input) throws IOException {
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

    private void printHelloWorld() throws IOException {
        System.out.println("Hello World!!!");
        printMenu();

    }


}
