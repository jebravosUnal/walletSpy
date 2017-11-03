package com.wallet.service;

import com.wallet.entity.Transaction;

import java.time.LocalDate;
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

    /**
     *
     * @param from
     * @param to
     * @return
     */
    List<Transaction> findTransactionsInDateRange(LocalDate from, LocalDate to);

    /**
     *
     * @param toInsert
     * @return
     */
//    TransactionDto insert(TransactionDto toInsert);

    Transaction insert(Transaction toInsert);

//    /**
//     *
//     * @param toInsert
//     * @return
//     */
//    List<TransactionDto> insert(List<TransactionDto> toInsert);

    void insertFakeTransaction();

}