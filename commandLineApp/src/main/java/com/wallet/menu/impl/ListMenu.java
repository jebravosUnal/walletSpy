package com.wallet.menu.impl;

import com.wallet.dto.DetailByCategory;
import com.wallet.dto.DetailByGroup;
import com.wallet.entity.Transaction;
import com.wallet.exceptions.WalletException;
import com.wallet.menu.Menu;
import com.wallet.service.TransactionService;
import com.wallet.utils.ConsoleUtils;
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
    private final static String LIST_CSV = "3";
    private final static String LIST_DETAILS_BY_CATEGORY = "4";
    private final static String LIST_DETAILS_BY_GROUPS = "5";

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
        println("-- " + LIST_CSV + ". List Transactions CSV");
        println("-- " + LIST_DETAILS_BY_CATEGORY + ". List details by category");
        println("-- " + LIST_DETAILS_BY_GROUPS + ". List details by group");
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
            case LIST_CSV:
                listCSV();
                break;
            case LIST_DETAILS_BY_CATEGORY:
                listDetailsByCategory();
                break;
            case LIST_DETAILS_BY_GROUPS:
                listDetailsByGroups();
                break;
            case PREVIOUS:
                printPreviousMenu();
                break;
            case EXIT:
                exit(NORMAL_EXIT_STATUTS);
            default:
                printNotValidOption();
        }
    }

    private void listDetailsByGroups() throws IOException, WalletException {
        List<DetailByGroup> details = transactionService.getDetailsByMatchingRulesGroups();
        details.forEach(group -> {
            if(group.getTransactionList().size() > 1){
                println("Pattern: " + group.getLabelMatched(), ConsoleUtils.ANSI_GREEN);
                group.getTransactionList().forEach(transaction -> println(transaction.getShortTransactionResume()));
            }
        });
        printMenu();
    }

    private void listDetailsByCategory() throws IOException, WalletException {
        List<DetailByCategory> details = transactionService.getDetailByCategoryList();
        details.forEach(detailByCategory -> {
            println("Category: " + detailByCategory.getCategory().getLabel()
                    + " #Trans: " + detailByCategory.getTransactionList().size()
                    + " total: " + detailByCategory.getTotalByCategory());
        });
        printMenu();
    }

    private void listCSV() throws IOException, WalletException {
        List<Transaction> transactionList = transactionService.findAllTransactions();
        listTransactionsCSV(transactionList);
        printMenu();
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

    private void listTransactionsCSV(List<Transaction> transactionList) {
        transactionList.forEach(transaction -> {
            if (transaction.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                println(transaction.getTransactionForCSV(), ANSI_GREEN);
            } else {
                println(transaction.getTransactionForCSV(), ANSI_RED);
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
