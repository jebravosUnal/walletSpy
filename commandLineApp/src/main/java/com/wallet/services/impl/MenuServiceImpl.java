package com.wallet.services.impl;

import com.wallet.exceptions.WalletException;
import com.wallet.menu.Menu;
import com.wallet.menu.impl.WalletMenu;
import com.wallet.service.factory.TransactionLoaderFactory;
import com.wallet.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by EBR3556 on 02/11/2017.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private TransactionLoaderFactory transactionLoaderFactory;

    @Override
    public void printMenu() throws IOException, WalletException {
        Menu walletMenu  = new WalletMenu(transactionLoaderFactory);
        walletMenu.printMenu();
    }
}
