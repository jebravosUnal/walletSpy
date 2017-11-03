package com.wallet.service.impl;

import com.wallet.dto.TransactionDto;
import com.wallet.entity.Transaction;
import com.wallet.document.Category;
import com.wallet.mapper.TransactionMapper;
import com.wallet.repository.FakeRepository;
import com.wallet.repository.TransactionRepository;
import com.wallet.service.TransactionService;
import fr.xebia.extras.selma.Selma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by EBR3556 on 12/09/2017.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private FakeRepository transactionRepository;
//    private TransactionRepository transactionRepository;

    private TransactionMapper transactionMapper;


    @Override
    public List<Transaction> findAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
//        transactions.forEach(t -> System.out.println(t.toString()));
        return transactions;
    }

    @Override
    public TransactionDto insert(TransactionDto toInsert) {
        transactionMapper = Selma.builder(TransactionMapper.class).build();
        Transaction t = null;
        t = transactionRepository.insert(transactionMapper.asTransaction(toInsert, t));
        return transactionMapper.asTransactionDto(t);
    }

    @Override
    public Transaction insert(Transaction toInsert) {
        return transactionRepository.insert(toInsert);
    }

    @Override
    public List<TransactionDto> insert(List<TransactionDto> toInsert) {
        transactionMapper = Selma.builder(TransactionMapper.class).build();
        List<Transaction> transactionsToInsert = newArrayList();
        toInsert.forEach(transactionDto -> {
            Transaction t = null;
            t = transactionMapper.asTransaction(transactionDto, t);
            transactionsToInsert.add(t);
        });

        List<TransactionDto> transactionInsertedDtos = newArrayList();
        transactionRepository.insert(transactionsToInsert).forEach(transaction -> {
            transactionInsertedDtos.add(transactionMapper.asTransactionDto(transaction));
        });

        return transactionInsertedDtos;

    }

    public void insertFakeTransaction() {
        Transaction t = new Transaction();
        t.setLabel("Transaccion del orto de prueba");
        t.setAmount(new BigDecimal(1000));
        t.setCategory(new Category());
        t.getCategory().setLabel("My category");
        t.setDate(LocalDateTime.now());
        transactionRepository.insert(t);
    }
}