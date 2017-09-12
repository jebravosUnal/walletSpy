package com.wallet.service.factory;

import com.wallet.exception.TransactionLoaderNotImplementedException;
import com.wallet.service.TransactionLoader;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.wallet.util.BundleKeys.MESSAGE_KEY;

/**
 * Created by EBR3556 on 12/09/2017.
 */
@Service
public class TransactionLoaderFactoryImpl  implements TransactionLoaderFactory{

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
}
