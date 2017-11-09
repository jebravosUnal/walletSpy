package com.wallet.dto;

import com.wallet.entity.Transaction;
import com.wallet.entity.TransactionGroupingRules;

import java.util.List;

/**
 * Created by EBR3556 on 09/11/2017.
 */
public class DetailByGroup {

    private String labelMatched;
    private TransactionGroupingRules rulesApplied;
    private List<Transaction> transactionList;

    public String getLabelMatched() {
        return labelMatched;
    }

    public void setLabelMatched(String labelMatched) {
        this.labelMatched = labelMatched;
    }

    public TransactionGroupingRules getRulesApplied() {
        return rulesApplied;
    }

    public void setRulesApplied(TransactionGroupingRules rulesApplied) {
        this.rulesApplied = rulesApplied;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
