package com.wallet.menu.impl;

import com.wallet.dto.TransactionDto;
import com.wallet.exceptions.TransactionLoaderNotImplementedException;
import com.wallet.exceptions.TransactionsLoadException;
import com.wallet.exceptions.WalletException;
import com.wallet.menu.Menu;
import com.wallet.service.TransactionLoader;
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

    // Constants
    private final static String LOAD_TRANSACTIONS = "1";
    // Attributes
    private String transactionsPath = "";
    // Services
    private TransactionLoaderFactory transactionLoaderFactory;

    // Initializer
    {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    // Constructor
    public WalletMenu(TransactionLoaderFactory transactionLoaderFactory) {
        this.transactionLoaderFactory = transactionLoaderFactory;
    }

    @Override
    public void printMenu() throws IOException, WalletException {
        System.out.println("--------------------------------------------------------");
        System.out.println("----------------------WALLET MENU-----------------------");
        System.out.println("-- 1. Load Transactions");
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
            case EXIT:
                exit(NORMAL_EXIT_STATUTS);
            default:
                printNotValidOption();
        }
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
        List<TransactionDto> transactionList = loader.loadAndGetTransactions();
        int transactionsLoadedCount = transactionList != null ? transactionList.size() : 0;
        System.out.println(transactionsLoadedCount + " transactions has been loaded");
    }

}
