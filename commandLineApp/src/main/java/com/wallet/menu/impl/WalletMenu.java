package com.wallet.menu.impl;

import com.wallet.dto.TransactionDto;
import com.wallet.entity.Transaction;
import com.wallet.exceptions.TransactionLoaderNotImplementedException;
import com.wallet.exceptions.TransactionsLoadException;
import com.wallet.exceptions.WalletException;
import com.wallet.menu.Menu;
import com.wallet.service.TransactionLoader;
import com.wallet.service.TransactionService;
import com.wallet.service.factory.TransactionLoaderFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.wallet.service.factory.impl.TransactionLoaderFactoryImpl.LoaderType.BNP_MESCOMPTES;

/**
 * Created by EBR3556 on 02/11/2017.
 */
public class WalletMenu extends Menu {

    public static class WalletMenuBuilder{

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

        public WalletMenu build(){
            WalletMenu walletMenu = new WalletMenu();
            walletMenu.setTransactionLoaderFactory(this.transactionLoaderFactory);
            walletMenu.setTransactionService(this.transactionService);
            return walletMenu;
        }

    }

    // Constants
    private final static String LOAD_TRANSACTIONS = "1";
    private final static String LIST_ALL_TRANSACTIONS = "2";
    // Attributes
    private String transactionsPath = "";
    // Services
    private TransactionLoaderFactory transactionLoaderFactory;
    private TransactionService transactionService;

    // Initializer
    {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    // Constructor
//    public WalletMenu(TransactionLoaderFactory transactionLoaderFactory) {
//        this.transactionLoaderFactory = transactionLoaderFactory;
//    }

    private WalletMenu() {

    }


    @Override
    public void printMenu() throws IOException, WalletException {
        System.out.println("--------------------------------------------------------");
        System.out.println("----------------------WALLET MENU-----------------------");
        System.out.println("-- 1. Load Transactions");
        System.out.println("-- 2. List All Transactions");
        System.out.println("-- " + EXIT + ". exit");
        System.out.println("--------------------------------------------------------");
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
            case EXIT:
                exit(NORMAL_EXIT_STATUTS);
            default:
                printNotValidOption();
        }
    }

    private void listAllTransactions() throws IOException, WalletException {
        listTransactions();
        printMenu();
    }

    private void listTransactions() {
        System.out.println("List of all transactions:");
        List<Transaction> transactionList = transactionService.findAllTransactions();
        transactionList.forEach(transaction -> System.out.println(transaction.getTransactionResume()));
    }

    private void loadProcess() throws IOException, WalletException {
        askForTransactionsPath();
        loadTransactions();
        printMenu();
    }

    private void askForTransactionsPath() throws IOException {
        System.out.println("--------------------------------------------------------");
        System.out.println("Enter transactions path:");
        transactionsPath = br.readLine();
        if (transactionsPath.isEmpty()) {
            askForTransactionsPath();
        }
    }

    private void loadTransactions() throws TransactionLoaderNotImplementedException, TransactionsLoadException {
        System.out.println("Loading transactions from " + transactionsPath);
        TransactionLoader loader = transactionLoaderFactory.getTransactionLoader(BNP_MESCOMPTES);
        List<Transaction> transactionList = loader.loadAndGetTransactions();
//        List<TransactionDto> transactionList = loader.loadAndGetTransactions();
        int transactionsLoadedCount = transactionList != null ? transactionList.size() : 0;
        System.out.println(transactionsLoadedCount + " transactions has been loaded");
    }

    public void setTransactionLoaderFactory(TransactionLoaderFactory transactionLoaderFactory) {
        this.transactionLoaderFactory = transactionLoaderFactory;
    }

    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
}
