package com.wallet.dto;

import com.wallet.document.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by EBR3556 on 21/09/2017.
 */
public class TransactionDto extends LabelledDto {

    private LocalDateTime date;
    private Category category;
    private String accountName;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
