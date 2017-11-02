package com.wallet.menu.impl;

import com.wallet.menu.Menu;
import com.wallet.menu.SubMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubMenuExample extends SubMenu {

    private final static String PRINT_HELLO_WORLD = "1";

    SubMenuExample(Menu previousMenu) {
        super(previousMenu);
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void printMenu() throws IOException {
        System.out.println("--------------------------------------------------------");
        System.out.println("------------------------SUBMENU-------------------------");
        System.out.println("-- 1. print Hola mundo from submenu");
        System.out.println("-- " + PREVIOUS + ". previous");
        System.out.println("-- " + EXIT + ". exit");
        System.out.println("--------------------------------------------------------");
        String input = br.readLine();
        handleInput(input);
    }

    @Override
    protected void handleInput(String input) throws IOException {
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

    private void printHelloWorld() throws IOException {
        System.out.println("Hello World from submenu!!!");
        printMenu();
    }
}
