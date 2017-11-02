package com.wallet.utils;

/**
 * Created by EBR3556 on 12/09/2017.
 */
public class Constants {

    // Transaction API constants
    public static final String TRANSACTIONS_BASE = "/transactions";
    public static final String INSERT_FAKE_TRANSACTION = TRANSACTIONS_BASE + "/insertFake";
    public static final String FIND_ALL_TRANSACTIONS = TRANSACTIONS_BASE + "/findAll";

    // Transaction Loader API constants
    public static final String TRANSACTION_LOADER_BASE = "/transactionLoader";
    public static final String TRANSACTION_LOADER_LOAD = TRANSACTION_LOADER_BASE + "/load/{format}";
}