package com.wallet.repository;

import com.wallet.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by EBR3556 on 12/09/2017.
 */
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    /**
     *
     * @param label
     * @return
     */
    Transaction findByLabel(String label);


    /**
     *
     * @param from
     * @param to
     * @return
     */
    List<Transaction> findTransactionsByDateIsBetween(LocalDate from, LocalDate to);
}