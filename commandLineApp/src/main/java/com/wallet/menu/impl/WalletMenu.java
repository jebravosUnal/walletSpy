package com.wallet.menu.impl;

import com.wallet.entity.Transaction;
import com.wallet.exceptions.TransactionLoaderNotImplementedException;
import com.wallet.exceptions.TransactionsLoadException;
import com.wallet.exceptions.WalletException;
import com.wallet.menu.Menu;
import com.wallet.service.TransactionLoader;
import com.wallet.service.TransactionService;
import com.wallet.service.factory.TransactionLoaderFactory;
import com.wallet.utils.DateUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import static com.wallet.service.factory.impl.TransactionLoaderFactoryImpl.LoaderType.BNP_MESCOMPTES;
import static com.wallet.utils.DateUtils.DEFAULT_DATE_PATTERN;

/**
 * Created by EBR3556 on 02/11/2017.
 */
public class WalletMenu extends Menu {

    public static class WalletMenuBuilder {

        private TransactionLoaderFactory transactionLoaderFactory;
        private TransactionService transactionService;

        public WalletMenuBuilder setTransactionLoaderFactory(TransactionLoaderFactory transactionLoaderFactory) {
            this.transactionLoaderFactory = transactionLoaderFactory;
            return this;
        }

        public WalletMenuBuilder setTransactionService(TransactionService transactionService) {
            this.transactionService = transactionService;
            return this;
        }

        public WalletMenu build() {
            WalletMenu walletMenu = new WalletMenu();
            walletMenu.setTransactionLoaderFactory(this.transactionLoaderFactory);
            walletMenu.setTransactionService(this.transactionService);
            return walletMenu;
        }

    }

    // Constants
    private final static String LOAD_TRANSACTIONS = "1";
    private final static String LIST_ALL_TRANSACTIONS = "2";
    private final static String LIST_TRANSACTIONS_IN_RANGE = "3";
    // Attributes
    private String transactionsPath = "";
    // Services
    private TransactionLoaderFactory transactionLoaderFactory;
    private TransactionService transactionService;

    // Initializer
    {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    private WalletMenu() {

    }


    @Override
    public void printMenu() throws IOException, WalletException {
        println("--------------------------------------------------------");
        println("----------------------WALLET MENU-----------------------");
        println("-- 1. Load Transactions");
        println("-- 2. List All Transactions");
        println("-- 3. List Transactions in date range (" + DEFAULT_DATE_PATTERN + ")");
        println("-- " + EXIT + ". exit");
        println("--------------------------------------------------------");
        String input = br.readLine();
        handleInput(input);
    }

    @Override
    protected void handleInput(String input) throws IOException, WalletException {
        switch (input) {
            case LOAD_TRANSACTIONS:
                loadProcess();
                break;
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
        transactionList.forEach(transaction -> println(transaction.getTransactionResume()));
        //
        printMenu();
    }

    private LocalDate askForDate(String message) throws IOException {
        println(message);
        LocalDate date = null;
        try{
            date = DateUtils.getLocalDateFromString(br.readLine());
        } catch (DateTimeParseException dte){
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
        transactionList.forEach(transaction -> println(transaction.getTransactionResume()));
    }

    private void loadProcess() throws IOException, WalletException {
        askForTransactionsPath();
        loadTransactions();
        printMenu();
    }

    private void askForTransactionsPath() throws IOException {
        println("--------------------------------------------------------");
        println("Enter transactions path:");
        transactionsPath = br.readLine();
        if (transactionsPath.isEmpty()) {
            askForTransactionsPath();
        }
    }

    private void loadTransactions() throws TransactionLoaderNotImplementedException, TransactionsLoadException {
        println("Loading transactions from " + transactionsPath);
        TransactionLoader loader = transactionLoaderFactory.getTransactionLoader(BNP_MESCOMPTES);
        List<Transaction> transactionList = loader.loadAndGetTransactions();
        int transactionsLoadedCount = transactionList != null ? transactionList.size() : 0;
        println(transactionsLoadedCount + " transactions has been loaded");
    }

    public void setTransactionLoaderFactory(TransactionLoaderFactory transactionLoaderFactory) {
        this.transactionLoaderFactory = transactionLoaderFactory;
    }

    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
}
