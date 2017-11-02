package com.wallet.services;

import com.wallet.exceptions.TransactionLoaderNotImplementedException;
import com.wallet.exceptions.TransactionsLoadException;
import com.wallet.exceptions.WalletException;

import java.io.IOException;

/**
 * Created by EBR3556 on 02/11/2017.
 */
public interface MenuService {


    void printMenu() throws IOException, WalletException;
}
