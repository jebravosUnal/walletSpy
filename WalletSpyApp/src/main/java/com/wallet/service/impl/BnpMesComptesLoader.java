package com.wallet.service.impl;

import com.wallet.exception.TransactionsLoadException;
import com.wallet.service.TransactionLoader;
import com.wallet.service.factory.TransactionLoaderFactory;
import org.springframework.stereotype.Service;

/**
 * Created by EBR3556 on 12/09/2017.
 */
@Service("BnpMesComptesLoader")
public class BnpMesComptesLoader implements TransactionLoader {

    @Override
    public void loadTransactions() throws TransactionsLoadException {

    }
}
