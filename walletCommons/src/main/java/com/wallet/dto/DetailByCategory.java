package com.wallet.dto;

import com.wallet.document.Category;
import com.wallet.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by EBR3556 on 05/11/2017.
 */
public class DetailByCategory {

    private Category category;
    private List<Transaction> transactionList;

    public DetailByCategory(Category category, List<Transaction> transactionList) {
        this.category = category;
        this.transactionList = transactionList;
    }

    public Double getTotalByCategory() {
        return transactionList.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .doubleValue();
    }

    public Category getCategory() {
        return category;
    }

//    public void setCategory(Category category) {
//        this.category = category;
//    }

    public List<Transaction> getTransactionList() {
        return newArrayList(transactionList);
    }

//    public void addTransactions(List<Transaction> toAdd){
//        if(isNotEmpty(toAdd)){
//            if(transactionList != null){
//                transactionList = newArrayList();
//            }
//
//            transactionList.addAll(toAdd);
//        }
//    }
}
