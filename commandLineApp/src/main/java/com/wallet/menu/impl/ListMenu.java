package com.wallet.menu.impl;

import com.wallet.entity.Transaction;
import com.wallet.exceptions.WalletException;
import com.wallet.menu.Menu;
import com.wallet.service.TransactionService;
import com.wallet.utils.DateUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import static com.wallet.utils.ConsoleUtils.*;
import static com.wallet.utils.DateUtils.DEFAULT_DATE_PATTERN;

/**
 * Created by EBR3556 on 05/11/2017.
 */
public class ListMenu extends Menu {

    private final static String LIST_ALL_TRANSACTIONS = "1";
    private final static String LIST_TRANSACTIONS_IN_RANGE = "2";

    // Services
    private TransactionService transactionService;

    public ListMenu(Menu previous, TransactionService transactionService) {
        super(previous);
        this.transactionService = transactionService;
    }

    @Override
    public void printMenu() throws IOException, WalletException {
        println("--------------------------------------------------------");
        println("----------------------LIST MENU-----------------------");
        println("-- " + LIST_ALL_TRANSACTIONS + ". List All Transactions");
        println("-- " + LIST_TRANSACTIONS_IN_RANGE + ". List Transactions in date range (" + DEFAULT_DATE_PATTERN + ")");
        println("-- " + PREVIOUS + ". previous");
        println("-- " + EXIT + ". exit");
        println("--------------------------------------------------------");
        String input = readLine();
        handleInput(input);
    }

    @Override
    protected void handleInput(String input) throws IOException, WalletException {
        switch (input) {
            case LIST_ALL_TRANSACTIONS:
                listAllTransactions();
                break;
            case LIST_TRANSACTIONS_IN_RANGE:
                listTransactionsInRange();
                break;
            case EXIT:
                exit(NORMAL_EXIT_STATUTS);
            default:
                printNotValidOption();
        }
    }

    private void listTransactionsInRange() throws IOException, WalletException {
        LocalDate initDate = askForDate("Enter init date");
        LocalDate endDate = askForDate("Enter end date");
        List<Transaction> transactionList = transactionService.findTransactionsInDateRange(initDate, endDate);
        listTransactions(transactionList);
        printMenu();
    }

    private void listTransactions(List<Transaction> transactionList) {
        transactionList.forEach(transaction -> {
            if (transaction.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                println(transaction.getTransactionResume(), ANSI_GREEN);
            } else {
                println(transaction.getTransactionResume(), ANSI_RED);
            }
        });
    }

    private LocalDate askForDate(String message) throws IOException {
        println(message);
        LocalDate date;
        try {
            date = DateUtils.getLocalDateFromString(readLine());
        } catch (DateTimeParseException dte) {
            return askForDate(message);
        }
        return date;
    }

    private void listAllTransactions() throws IOException, WalletException {
        listTransactions();
        printMenu();
    }

    private void listTransactions() {
        println("List of all transactions:");
        List<Transaction> transactionList = transactionService.findAllTransactions();
        listTransactions(transactionList);
    }
}
