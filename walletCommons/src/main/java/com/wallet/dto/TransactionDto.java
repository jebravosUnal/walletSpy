package com.wallet.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by EBR3556 on 21/09/2017.
 */
public class TransactionDto extends LabelledDto {

    private LocalDateTime date;
    private CategoryDto category;
    private BigDecimal amount;
    private String userId;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
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
}
