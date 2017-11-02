package com.wallet.document;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by EBR3556 on 22/09/2017.
 */
@Document
public class UploadInformation {

    private LocalDateTime date;
    private BigDecimal balance;
    private Long transactionsLoaded;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getTransactionsLoaded() {
        return transactionsLoaded;
    }

    public void setTransactionsLoaded(Long transactionsLoaded) {
        this.transactionsLoaded = transactionsLoaded;
    }
}
