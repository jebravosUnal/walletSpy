package com.wallet.entity;

import com.wallet.document.Account;
import com.wallet.document.Category;
import org.springframework.data.domain.Example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.function.Predicate;

/**
 * Created by EBR3556 on 12/09/2017.
 */
public class Transaction extends LabeledEntity {

    private LocalDate date;
    private Category category;
    private Account account;
    private BigDecimal amount;
    private String userId;

    public static Comparator<Transaction> orderByDate() {
        return Comparator.comparing(Transaction::getDate);
    }

    public static Predicate<Transaction> isLabel(String label) {
        return transaction -> transaction.getLabel().equals(label);
    }

    public static Predicate<Transaction> isId(String id) {
        return transaction -> transaction.getId().equals(id);
    }

    public static Predicate<Transaction> isDateEqualOrAfterThan(LocalDate date) {
        return transaction -> transaction.getDate().isEqual(date) || transaction.getDate().isAfter(date);
    }

    public static Predicate<Transaction> isEqual(Transaction toCompare) {
        return transaction -> transaction.equals(toCompare);
    }

    public static Predicate<Transaction> isDateEqualOrBeforeThan(LocalDate date) {
        return transaction -> transaction.getDate().isEqual(date) || transaction.getDate().isBefore(date);
    }

    public static Predicate<Transaction> isDateBetweenRange(LocalDate from, LocalDate to) {
//        return transaction -> transaction.getDate().toLocalDate().isEqual(from) || transaction.getDate().toLocalDate().isAfter(from)
//                 && transaction.getDate().toLocalDate().isEqual(to) || transaction.getDate().toLocalDate().isBefore(to);

        return isDateEqualOrAfterThan(from).and(isDateEqualOrBeforeThan(to));

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    // Comparators

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Predicates

    public Transaction clone() {
        Transaction clone = new Transaction();
        clone.setUserId(this.userId);
        clone.setLabel(this.getLabel());
        clone.setAmount(this.getAmount());
        clone.setCategory(this.getCategory());
        return clone;
    }

    public Example<Transaction> getExample(){
        Transaction clone = this.clone();
        //ignore fields for compare
        clone.setCategory(null);
        //
        return Example.of(clone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;

        Transaction that = (Transaction) o;

        if (!getDate().equals(that.getDate())) return false;
//        if (!getCategory().equals(that.getCategory())) return false;
//        if (!account.equals(that.account)) return false;
        if (!getAmount().equals(that.getAmount())) return false;
        if (!getLabel().equalsIgnoreCase(that.getLabel())) return false;
        return getUserId().equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        int result = getDate().hashCode();
//        result = 31 * result + getCategory().hashCode();
//        result = 31 * result + account.hashCode();
        result = 31 * result + getAmount().hashCode();
        result = 31 * result + getLabel().hashCode();
        result = 31 * result + getUserId().hashCode();
        return result;
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
                + (this.getUserId() != null ? " userId : " + this.getUserId() + " - " : "");
    }

    public String getShortTransactionResume() {
        return "" + (this.getId() != null ? this.getId() + " - " : "")
                + (this.getDate() != null ? this.getDate() + " - " : "")
                + (this.getLabel() != null ? this.getLabel() + " - " : "")
                + (this.getAmount() != null ? this.getAmount() + " - " : "")
                + (this.getCategory() != null ? this.getCategory().getLabel() + " - " : "")
                + (this.getUserId() != null ? this.getUserId() + " - " : "");
    }

    public String getTransactionForCSV() {
        return "" + (this.getId() != null ? this.getId() + ";" : ";")
                + (this.getDate() != null ? this.getDate() + ";" : ";")
                + (this.getLabel() != null ? this.getLabel() + ";" : ";")
                + (this.getAmount() != null ? this.getAmount() + ";" : ";")
                + (this.getCategory() != null ? this.getCategory().getLabel() + ";" : ";")
                + (this.getUserId() != null ? this.getUserId() + "" : "")
                ;
    }

}