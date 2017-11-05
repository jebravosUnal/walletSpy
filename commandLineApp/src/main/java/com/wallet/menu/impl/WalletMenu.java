package com.wallet.menu.impl;

import com.wallet.entity.Transaction;
import com.wallet.exceptions.WalletException;
import com.wallet.menu.Menu;
import com.wallet.service.TransactionService;
import com.wallet.service.factory.TransactionLoaderFactory;
import com.wallet.utils.DateUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import static com.wallet.utils.ConsoleUtils.*;
import static com.wallet.utils.DateUtils.DEFAULT_DATE_PATTERN;

/**
 * Created by EBR3556 on 02/11/2017.
 */
public class WalletMenu extends Menu {

    // Constants
    private final static String LOAD_TRANSACTIONS = "1";
    private final static String LIST_TRANSACTIONS = "2";
    // Services
    private TransactionLoaderFactory transactionLoaderFactory;
    private TransactionService transactionService;

    private WalletMenu() {
    }

    public WalletMenu(TransactionLoaderFactory transactionLoaderFactory, TransactionService transactionService) {
        this.transactionLoaderFactory = transactionLoaderFactory;
        this.transactionService = transactionService;
    }

    @Override
    public void printMenu() throws IOException, WalletException {
        println("--------------------------------------------------------");
        println("----------------------WALLET MENU-----------------------");
        println("-- 1. Load Transactions");
        println("-- 2. List Transactions");
        println("-- " + EXIT + ". exit");
        println("--------------------------------------------------------");
        String input = readLine();
        handleInput(input);
    }

    @Override
    protected void handleInput(String input) throws IOException, WalletException {
        switch (input) {
            case LOAD_TRANSACTIONS:
                showLoadMenu();
                break;
            case LIST_TRANSACTIONS:
                showListMenu();
                break;
            case EXIT:
                exit(NORMAL_EXIT_STATUTS);
            default:
                printNotValidOption();
        }
    }

    private void showListMenu() throws IOException, WalletException {
        new ListMenu(this, transactionService).printMenu();
    }

    private void showLoadMenu() throws IOException, WalletException {
        new LoadMenu(this, transactionLoaderFactory).printMenu();
//        new LoadMenu(this, transactionLoaderFactory, transactionService).printMenu();
    }
}
