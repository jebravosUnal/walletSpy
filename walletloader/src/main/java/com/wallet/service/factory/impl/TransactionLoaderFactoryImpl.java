package com.wallet.service.factory.impl;

import com.wallet.exceptions.TransactionLoaderNotImplementedException;
import com.wallet.service.TransactionLoader;
import com.wallet.service.factory.TransactionLoaderFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.wallet.utils.BundleKeys.MESSAGE_KEY;

/**
 * Created by EBR3556 on 21/09/2017.
 */
@Service
public class TransactionLoaderFactoryImpl  implements TransactionLoaderFactory {

    @Resource(name = "BnpMesComptesLoader")
    private TransactionLoader bnpMesComptesLoader;

    public enum LoaderType {
        BNP_MESCOMPTES
    }

    @Override
    public TransactionLoader getTransactionLoader(LoaderType loaderType) throws TransactionLoaderNotImplementedException {
        switch(loaderType){
            case BNP_MESCOMPTES:
                return bnpMesComptesLoader;
            default:
                throw new TransactionLoaderNotImplementedException(MESSAGE_KEY);
        }
    }

    @Override
    public TransactionLoader getTransactionLoader(String loaderType) throws TransactionLoaderNotImplementedException {
        switch(loaderType){
            case "BNP":
                return bnpMesComptesLoader;
            default:
                throw new TransactionLoaderNotImplementedException(MESSAGE_KEY);
        }
    }
}
