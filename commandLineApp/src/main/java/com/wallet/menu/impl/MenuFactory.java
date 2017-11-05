//package com.wallet.menu.impl;
//
//import com.wallet.menu.Menu;
//import com.wallet.menu.impl.WalletMenu;
//import com.wallet.service.TransactionService;
//import com.wallet.service.factory.TransactionLoaderFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * Created by EBR3556 on 05/11/2017.
// */
//@Component
//public class MenuFactory {
//
//    @Autowired
//    private TransactionLoaderFactory transactionLoaderFactory;
//    @Autowired
//    private TransactionService transactionService;
//
//    public Menu getMenu(MenuEnum menu) {
//        return getMenu(null, menu);
//    }
//
//    public Menu getMenu(Menu previousMenu, MenuEnum menu) {
//        switch (menu) {
////          case LIST:
////               break;
//            case LOAD:
//                return new LoadMenu(previousMenu, transactionLoaderFactory, transactionService);
//            case WALLET:
//                return new WalletMenu(transactionLoaderFactory, transactionService);
////          default:
////              break;
//        }
//        return null;
//    }
//
//    public enum MenuEnum {
//        WALLET, LOAD, LIST
//    }
//
//
//}
