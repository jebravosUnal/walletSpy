package com.wallet.menu.impl;

import com.wallet.entity.Transaction;
import com.wallet.exceptions.TransactionLoaderNotImplementedException;
import com.wallet.exceptions.TransactionsLoadException;
import com.wallet.exceptions.WalletException;
import com.wallet.menu.Menu;
import com.wallet.menu.SubMenu;
import com.wallet.service.TransactionLoader;
import com.wallet.service.TransactionService;
import com.wallet.service.factory.TransactionLoaderFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.wallet.service.factory.impl.TransactionLoaderFactoryImpl.LoaderType.BNP_MESCOMPTES;

public class LoadMenu extends SubMenu {

    public static class LoadMenuBuilder {

        private TransactionLoaderFactory transactionLoaderFactory;
        private TransactionService transactionService;

        public LoadMenuBuilder setTransactionLoaderFactory(TransactionLoaderFactory transactionLoaderFactory) {
            this.transactionLoaderFactory = transactionLoaderFactory;
            return this;
        }

        public LoadMenuBuilder setTransactionService(TransactionService transactionService) {
            this.transactionService = transactionService;
            return this;
        }

        public LoadMenu build(Menu previousMenu) {
            LoadMenu loadMenu = new LoadMenu(previousMenu);
            loadMenu.setTransactionLoaderFactory(this.transactionLoaderFactory);
            loadMenu.setTransactionService(this.transactionService);
            return loadMenu;
        }

    }

    {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    private final static String LOAD_TRANSACTIONS_FROM_HARDCODED = "1";
    private final static String LOAD_TRANSACTIONS_FROM_FOLDER = "2";

    // Services
    private TransactionLoaderFactory transactionLoaderFactory;
    private TransactionService transactionService;
    // Attributes
    private String transactionsPath = "";

    public LoadMenu(Menu previousMenu) {
        super(previousMenu);
    }

    @Override
    public void printMenu() throws IOException, WalletException {
        println("--------------------------------------------------------");
        println("----------------------LOAD MENU-----------------------");
        println("-- " + LOAD_TRANSACTIONS_FROM_HARDCODED + ". Load Transactions from HARDCODED file");
        println("-- " + LOAD_TRANSACTIONS_FROM_FOLDER + ". Load Transactions from folder");
        println("-- " + PREVIOUS + ". previous");
        println("-- " + EXIT + ". exit");
        println("--------------------------------------------------------");
        String input = br.readLine();
        handleInput(input);
    }

    @Override
    protected void handleInput(String input) throws IOException, WalletException {
        switch (input) {
            case LOAD_TRANSACTIONS_FROM_HARDCODED:
                loadProcessFromHardCoded();
                break;
            case LOAD_TRANSACTIONS_FROM_FOLDER:
                loadProcessFromFolder();
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

    private void loadProcessFromFolder() throws IOException, WalletException {
//        println("Not Implemented Yet");
        String directory = askForTransactionsPath();
        loadTransactionsFromDirectory(directory);
        printMenu();
    }

    private void loadProcessFromHardCoded() throws IOException, WalletException {
//        askForTransactionsPath();
        loadTransactions();
        printMenu();
    }

    private String askForTransactionsPath() throws IOException {
        println("--------------------------------------------------------");
        println("Enter transactions path:");
        String transactionsPath = br.readLine();
        if (transactionsPath.isEmpty()) {
            return askForTransactionsPath();
        } else {
            return transactionsPath;
        }
    }

    private void loadTransactionsFromDirectory(String directory) throws WalletException {
        println("Loading transactions from " + directory);
        TransactionLoader loader = transactionLoaderFactory.getTransactionLoader(BNP_MESCOMPTES);
        List<Transaction> transactionList = loader.loadFromDirectoryAndGetTransactions(directory);
        int transactionsLoadedCount = transactionList != null ? transactionList.size() : 0;
        println(transactionsLoadedCount + " transactions has been loaded");
    }

    private void loadTransactions() throws TransactionLoaderNotImplementedException, TransactionsLoadException {
        println("Loading transactions from " + transactionsPath);
        TransactionLoader loader = transactionLoaderFactory.getTransactionLoader(BNP_MESCOMPTES);
        List<Transaction> transactionList = loader.loadAndGetTransactions();
        int transactionsLoadedCount = transactionList != null ? transactionList.size() : 0;
        println(transactionsLoadedCount + " transactions has been loaded");
    }

    void setTransactionLoaderFactory(TransactionLoaderFactory transactionLoaderFactory) {
        this.transactionLoaderFactory = transactionLoaderFactory;
    }

    void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
}
