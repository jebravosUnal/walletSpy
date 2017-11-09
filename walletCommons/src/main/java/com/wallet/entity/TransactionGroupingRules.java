package com.wallet.entity;

/**
 * Created by EBR3556 on 09/11/2017.
 */
public class TransactionGroupingRules extends AbstractEntity{

    private String userId;
    private Integer wordsNumberToConsiderSameTransaction;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getWordsNumberToConsiderSameTransaction() {
        return wordsNumberToConsiderSameTransaction;
    }

    public void setWordsNumberToConsiderSameTransaction(Integer wordsNumberToConsiderSameTransaction) {
        this.wordsNumberToConsiderSameTransaction = wordsNumberToConsiderSameTransaction;
    }
}
