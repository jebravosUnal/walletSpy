package com.wallet.service;

import com.wallet.document.Category;
import com.wallet.dto.DetailByCategory;
import com.wallet.entity.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    List<DetailByCategory> getDetailByCategoryList();

    /**
     * It will upsert the entity
     * @param toSave
     * @return
     */
    Transaction save(Transaction toSave);

    /**
     *
     * @param transaction
     * @return inserted transaction, null otherwise
     */
    Optional<Transaction> insertOrUpdateCategoryIfExists(Transaction transaction);

    boolean exist(Transaction transactionProbe);

    Transaction findOne(Transaction transactionProbe);

//    void insertFakeTransaction();

}