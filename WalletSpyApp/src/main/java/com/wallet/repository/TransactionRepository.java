package com.wallet.repository;

import com.wallet.domain.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

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
}
