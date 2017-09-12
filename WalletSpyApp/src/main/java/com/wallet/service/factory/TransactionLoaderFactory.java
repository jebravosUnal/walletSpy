package com.wallet.service.factory;

import com.wallet.exception.TransactionLoaderNotImplementedException;
import com.wallet.service.TransactionLoader;
import com.wallet.service.factory.TransactionLoaderFactoryImpl.LoaderType;

/**
 * Created by EBR3556 on 12/09/2017.
 */
public interface TransactionLoaderFactory {

    /**
     *
     * @param loaderType
     * @return
     */
    TransactionLoader getTransactionLoader(LoaderType loaderType) throws TransactionLoaderNotImplementedException;
}
