//package com.wallet.controller;
//
//import com.wallet.dto.TransactionDto;
//import com.wallet.exceptions.TransactionLoaderNotImplementedException;
//import com.wallet.exceptions.TransactionsLoadException;
//import com.wallet.service.TransactionLoader;
//import com.wallet.service.factory.TransactionLoaderFactory;
//import com.wallet.utils.Constants;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.List;
//
///**
// * Created by EBR3556 on 21/09/2017.
// */
//@Controller
//public class LoaderController {
//
//    private final static Logger LOGGER = LogManager.getLogger(LoaderController.class);
//
//    @Autowired
//    private TransactionLoaderFactory transactionLoaderFactory;
//
//    @RequestMapping(value = Constants.TRANSACTION_LOADER_LOAD, method = RequestMethod.GET)
//    public ResponseEntity<List<TransactionDto>> loadTransactions(@PathVariable String format) throws TransactionLoaderNotImplementedException, TransactionsLoadException {
//        TransactionLoader loader = transactionLoaderFactory.getTransactionLoader(format);
//        return new ResponseEntity<>(loader.loadAndGetTransactions(), HttpStatus.OK);
//    }
//}
