package com.wallet.service.impl;

import com.wallet.document.Category;
import com.wallet.dto.DetailByCategory;
import com.wallet.dto.DetailByGroup;
import com.wallet.entity.Transaction;
import com.wallet.entity.TransactionGroupingRules;
import com.wallet.repository.FakeRepository;
import com.wallet.repository.TransactionRepository;
import com.wallet.service.TransactionService;
import com.wallet.utils.ConsoleUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.wallet.utils.ConsoleUtils.println;

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
    public List<DetailByCategory> getDetailByCategoryList() {
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
            DetailByCategory d = new DetailByCategory(category, transactionsByCategory);
            detailByCategories.add(d);
        });

        return detailByCategories;
    }

    @Override
    public Transaction save(Transaction toSave) {
        return transactionRepository.save(toSave);
    }

    @Override
    public Optional<Transaction> insertOrUpdateCategoryIfExists(Transaction transaction) {
        if (exist(transaction)) {
            Transaction existent = findOne(transaction);
            if (transaction.getCategory().isDefined() && !existent.getCategory().isDefined()) {
                LOGGER.info("Updating category -> " + transaction.getTransactionResume());
                existent.setCategory(transaction.getCategory());
                return Optional.of(save(existent));
            } else {
                LOGGER.debug("Excluded -> " + transaction.getTransactionResume());
                return Optional.empty();
            }
        } else {
            return Optional.of(save(transaction));
        }
    }

    @Override
    public boolean exist(Transaction transactionProbe) {
        return findOne(transactionProbe) != null;
//        return transactionRepository.findOne(Example.of(transactionProbe)) != null;
    }

    @Override
    public Transaction findOne(Transaction transactionProbe) {
        return transactionRepository.findOne(transactionProbe.getExample());
//        return transactionRepository.findOne(Example.of(transactionProbe));

    }

    //TODO refactor the method...user the TransactionGroupingRules class to define the rules
    //TODO refactor the method... split the method in smaller and more generic methods
    @Override
    public List<DetailByGroup> getDetailsByMatchingRulesGroups() {
        Long init = System.nanoTime();
        List<DetailByGroup> detailByGroups = newArrayList();
        LOGGER.info("-----------------------------------------------------------");

        Map<String, List<Transaction>> tokenToStringMap = newHashMap();

        List<Transaction> transactions = findAllTransactions();
        transactions.stream()
                .map(transaction -> {
                    return Stream.of(transaction.getLabel().split("\\s+"))
                            .limit(6)
                            .reduce((s1, s2) -> s1 + " " + s2)
                            .map(s -> s.replaceAll("ECH\\/[0-3][0-9][0-1][0-9][0-9][0-9]", "ECH\\@@@@@@"))
                            .orElse("");
                })
                .distinct()
                .forEach(s -> tokenToStringMap.putIfAbsent(s, newArrayList()));

        tokenToStringMap.forEach((pattern, groupedTransactions) -> {
            groupedTransactions.addAll(transactions.stream()
                    .filter(transaction -> transaction.getLabel()
                            .replaceAll("ECH\\/[0-3][0-9][0-1][0-9][0-9][0-9]", "ECH\\@@@@@@")
                            .trim().regionMatches(true, 0, pattern.trim(), 0, pattern.trim().length()))
                    .collect(Collectors.toList()));
        });

        final long duration = System.nanoTime() - init;
        final double seconds = ((double) duration / 1000000000);
        LOGGER.info("-----------------------------------------------------------------");
        LOGGER.info("solution Time : " + new DecimalFormat("#.##########").format(seconds) + " Seconds");
        LOGGER.info("-----------------------------------------------------------------");


        tokenToStringMap
                .forEach((pattern, groupedTransactions) -> {
//                    if (groupedTransactions.size() > 1) {
                        DetailByGroup detail = new DetailByGroup();
                        detail.setLabelMatched(pattern);
                        detail.setTransactionList(groupedTransactions);
                        detailByGroups.add(detail);
//                    }
                });
        return detailByGroups;
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