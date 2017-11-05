package com.wallet.service.impl;

import com.wallet.document.Category;
import com.wallet.dto.DetailByCategory;
import com.wallet.entity.Transaction;
import com.wallet.repository.FakeRepository;
import com.wallet.repository.TransactionRepository;
import com.wallet.service.TransactionService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by EBR3556 on 12/09/2017.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private final static Logger LOGGER = LogManager.getLogger(TransactionServiceImpl.class);

    @Autowired
//    private FakeRepository transactionRepository;
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        transactions.sort(Transaction.orderByDate());
        return transactions;
    }

    @Override
    public List<Transaction> findTransactionsInDateRange(LocalDate from, LocalDate to) {
        return transactionRepository.findTransactionsByDateIsBetween(from, to);
    }

    @Override
    public List<DetailByCategory> getDetailByCategoryList(){
        List<DetailByCategory> detailByCategories = newArrayList();
        List<Transaction> allTransactionsByUserId = transactionRepository.findAllByUserId("jebravos");

        List<Category> categories = allTransactionsByUserId.stream()
                .map(Transaction::getCategory)
                .distinct()
                .collect(Collectors.toList());

        categories.forEach(category -> {
            List<Transaction> transactionsByCategory = allTransactionsByUserId.stream()
                    .filter(transaction -> transaction.getCategory().equals(category))
                    .collect(Collectors.toList());
            DetailByCategory  d = new DetailByCategory(category, transactionsByCategory);
            detailByCategories.add(d);
        });

        return detailByCategories;
    }

    @Override
    public Transaction insert(Transaction toInsert) {
        return transactionRepository.insert(toInsert);
    }

    @Override
    public Optional<Transaction> insertIfDontExist(Transaction transaction){
        if(transactionRepository.findOne(Example.of(transaction)) != null){
//        if(transactionRepository.exists(transaction)){
            LOGGER.debug("Excluded -> " + transaction.getTransactionResume());
            return Optional.empty();
        } else {
            return Optional.of(insert(transaction));
        }
    }

//    public void insertFakeTransaction() {
//        Transaction t = new Transaction();
//        t.setLabel("Transaccion del orto de prueba");
//        t.setAmount(new BigDecimal(1000));
//        t.setCategory(new Category());
//        t.getCategory().setLabel("My category");
//        t.setDate(LocalDate.now());
////        t.setDate(LocalDateTime.now());
//        transactionRepository.insert(t);
//    }
}