package com.wallet.service.factory;

import com.wallet.exceptions.TransactionLoaderNotImplementedException;
import com.wallet.service.TransactionLoader;
import com.wallet.service.factory.impl.TransactionLoaderFactoryImpl.LoaderType;

/**
 * Created by EBR3556 on 21/09/2017.
 */
public interface TransactionLoaderFactory {

    /**
     *
     * @param loaderType
     * @return
     */
    TransactionLoader getTransactionLoader(LoaderType loaderType) throws TransactionLoaderNotImplementedException;

    /**
     *
     * @param loaderType
     * @return
     * @throws TransactionLoaderNotImplementedException
     */
    TransactionLoader getTransactionLoader(String loaderType) throws TransactionLoaderNotImplementedException;
}
