package com.wallet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.wallet.util.Constants.TRANSACTION_LOADER_LOAD;

/**
 * Created by EBR3556 on 12/09/2017.
 */
@Controller
public class TransactionLoaderController {

    @RequestMapping(value = TRANSACTION_LOADER_LOAD, method = RequestMethod.GET)
    public void loadFile(@PathVariable String format){

    }
}
