//package com.wallet;
//
//import com.wallet.exceptions.TransactionLoaderNotImplementedException;
//import com.wallet.service.TransactionLoader;
//import com.wallet.service.factory.TransactionLoaderFactory;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static com.wallet.service.factory.impl.TransactionLoaderFactoryImpl.LoaderType.BNP_MESCOMPTES;
//
///**
// * Created by EBR3556 on 21/09/2017.
// */
//public class WalletLoader {
//
//    private final static Logger LOGGER = LogManager.getLogger(WalletLoader.class);
//
//    @Autowired
//    private TransactionLoaderFactory transactionLoaderFactory;
//
//
//    public static void main(String[] args) throws TransactionLoaderNotImplementedException {
//        WalletLoader walletLoader = new WalletLoader();
//        TransactionLoader loader = walletLoader.transactionLoaderFactory.getTransactionLoader(BNP_MESCOMPTES);
//    }
//}
