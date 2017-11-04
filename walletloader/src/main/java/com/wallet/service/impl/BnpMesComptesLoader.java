package com.wallet.service.impl;

import com.wallet.document.Category;
import com.wallet.entity.Transaction;
import com.wallet.exceptions.TransactionsLoadException;
import com.wallet.service.TransactionLoader;
import com.wallet.service.TransactionService;
import com.wallet.utils.ExcelReader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static com.wallet.utils.DateUtils.getLocalDateFromString;
import static com.wallet.utils.DateUtils.getLocalDateTimeFromString;

/**
 * Created by EBR3556 on 21/09/2017.
 */
@Service("BnpMesComptesLoader")
public class BnpMesComptesLoader implements TransactionLoader {

    private final static Logger LOGGER = LogManager.getLogger(BnpMesComptesLoader.class);

    private static final int BALANCE_ROW_INDEX = 0;
    private static final int HEADERS_ROW_INDEX = 2;
    private static final int TRANSACTIONS_FIRST_ROW_INDEX = HEADERS_ROW_INDEX + 1;
    private static final int DATE_COLUMN_INDEX = 0;
    private static final int CATEGORY_COLUMN_INDEX = 1;
    private static final int LABEL_COLUMN_INDEX = 2;
    private static final int AMOUNT_COLUMN_INDEX = 3;

    private static String DATE_PATTERN = "dd-MM-yyyy";


    //TODO for testing only
//    private static String FILE_NAME = "export_03_11_2017_13_46_25.xls";
    private static String FILE_NAME = "export_12_09_2017_10_34_07.xls";

//    @Value("${wallet.file.repository}")
//    private final static String FILES_PATH = "C:\\Users\\esteban.bravo\\Downloads\\";
    private final static String FILES_PATH = "C:/Users/ebr3556/Desktop/Esteban/Documents/Personnel/transactiones/";

    @Autowired
    private TransactionService transactionService;

    @Override
    public void loadTransactions() throws TransactionsLoadException {
        LOGGER.warn("Not implemented...");
    }

    @Override
    public List<Transaction> loadAndGetTransactions() throws TransactionsLoadException {
        LOGGER.info("Loading...");
        List<Transaction> transactionsRead = newArrayList();
        List<Transaction> transactionsLoaded = newArrayList();
        //
        ExcelReader reader = new ExcelReader(FILES_PATH + FILE_NAME);

        List<Map<Integer, String>> transactionsFileContent = reader.getAllRowsContent(TRANSACTIONS_FIRST_ROW_INDEX);
        transactionsFileContent.forEach(content -> {
            if(content != null){
                Transaction transformedTransaction = fromContentMapToTransaction(content);
                if(transformedTransaction != null){
                    transactionsRead.add(transformedTransaction);
                    transactionService.insertIfDontExist(transformedTransaction).ifPresent(transaction -> transactionsLoaded.add(transaction));

                }
            }
        });

        reader.close();
        //
        return transactionsLoaded;
    }



    private Transaction fromContentMapToTransaction(Map<Integer, String> contentMap){
        Transaction transaction = new Transaction();
        if(contentMap.isEmpty()){
            return null;
        }

        // TODO User HardCoded
        transaction.setUserId("jebravos");

        LocalDate transactionDate = getLocalDateFromString(contentMap.get(DATE_COLUMN_INDEX), DATE_PATTERN);
        transaction.setDate(transactionDate);

        Category category = new Category();
        category.setLabel(contentMap.get(CATEGORY_COLUMN_INDEX));
        transaction.setCategory(category);

        transaction.setLabel(contentMap.get(LABEL_COLUMN_INDEX));

        BigDecimal transactionAmount = new BigDecimal(contentMap.get(AMOUNT_COLUMN_INDEX));
        transaction.setAmount(transactionAmount);

        return transaction;
    }

}
