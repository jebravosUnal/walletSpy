package com.wallet.service;

import com.wallet.domain.entity.Transaction;

import java.util.List;

/**
 * Created by EBR3556 on 12/09/2017.
 */
public interface TransactionService {

    /**
     * It will get all transactions inside the Collection Transaction.
     * This method wont be used to send data to the front. Methods that return the specific data inside DTOs should be created.
     * @return A list with the transactions found
     */
    List<Transaction> findAllTransactions();

    void insertFakeTransaction();

}
