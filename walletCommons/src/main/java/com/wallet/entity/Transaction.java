package com.wallet.entity;

import com.wallet.document.Account;
import com.wallet.document.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by EBR3556 on 12/09/2017.
 */
public class Transaction extends LabeledEntity {

    private LocalDateTime date;
    private Category category;
    private Account account;
    private BigDecimal amount;
    private String userId;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{ "
                + (this.getId() != null ? " id : " + this.getId() + ", \n" : "")
                + (this.getDate() != null ? " date : " + this.getDate() + " \n" : "")
                + (this.getLabel() != null ? " label : " + this.getLabel() + " \n" : "")
                + (this.getAmount() != null ? " amount : " + this.getAmount() + " \n" : "")
                + (this.getCategory() != null ? " category : " + this.getCategory().toString() + " \n" : "")
                + (this.getUserId() != null ? " category : " + this.getUserId() + " \n" : "")
                + "}";
    }

    public String getTransactionResume() {
        return "" + (this.getId() != null ? " id : " + this.getId() + " - " : "")
                + (this.getDate() != null ? " date : " + this.getDate() + " - " : "")
                + (this.getLabel() != null ? " label : " + this.getLabel() + " - " : "")
                + (this.getAmount() != null ? " amount : " + this.getAmount() + " - " : "")
                + (this.getCategory() != null ? " category : " + this.getCategory().getLabel() + " - " : "")
                + (this.getUserId() != null ? " category : " + this.getUserId() + " - " : "");
    }

}