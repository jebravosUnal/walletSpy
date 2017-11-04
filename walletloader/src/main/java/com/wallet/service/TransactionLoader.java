package com.wallet.service;

import com.wallet.entity.Transaction;
import com.wallet.exceptions.TransactionsLoadException;

import java.util.List;

/**
 * Created by EBR3556 on 21/09/2017.
 */
public interface TransactionLoader {

    void loadTransactions() throws TransactionsLoadException;

    List<Transaction> loadAndGetTransactions() throws TransactionsLoadException;
//    List<TransactionDto> loadAndGetTransactions() throws TransactionsLoadException;
}
