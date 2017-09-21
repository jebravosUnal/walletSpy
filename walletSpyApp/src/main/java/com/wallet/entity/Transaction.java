package com.wallet.entity;

import com.wallet.entity.document.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by EBR3556 on 12/09/2017.
 */
public class Transaction extends LabeledEntity {

    private LocalDateTime date;
    private Category category;
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

}