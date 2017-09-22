package com.wallet.service.impl;

import com.wallet.entity.Transaction;
import com.wallet.document.Category;
import com.wallet.repository.TransactionRepository;
import com.wallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by EBR3556 on 12/09/2017.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        transactions.forEach(t -> System.out.println(t.toString()));
        return transactions;
    }

    public void insertFakeTransaction(){
        Transaction t = new Transaction();
        t.setLabel("Transaccion del orto de prueba");
        t.setAmount(new BigDecimal(1000));
        t.setCategory(new Category());
        t.getCategory().setLabel("My category");
        t.setDate(LocalDateTime.now());
        transactionRepository.insert(t);
    }
}