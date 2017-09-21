package com.wallet.controller;

import com.wallet.entity.Transaction;
import com.wallet.service.TransactionService;
import com.wallet.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by EBR3556 on 12/09/2017.
 */
@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = Constants.INSERT_FAKE_TRANSACTION, method = RequestMethod.GET)
    public void insertFakeTransaction(){
        transactionService.insertFakeTransaction();
    }

    @RequestMapping(value = Constants.FIND_ALL_TRANSACTIONS, method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> findAllTransactions(){
        return new ResponseEntity<List<Transaction>>(transactionService.findAllTransactions(), HttpStatus.OK);
    }
}