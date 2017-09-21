package com.wallet.service.impl;

import com.wallet.dto.CategoryDto;
import com.wallet.dto.TransactionDto;
import com.wallet.exceptions.TransactionsLoadException;
import com.wallet.service.TransactionLoader;
import com.wallet.utils.ExcelReader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
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
    private static String FILE_NAME = "export_12_09_2017_10_34_07.xls";

    @Value("${wallet.file.repository}")
    private String filesPath;


    @Override
    public void loadTransactions() throws TransactionsLoadException {
        LOGGER.debug("Hello from bnp loader...");
    }

    @Override
    public List<TransactionDto> loadAndGetTransactions() throws TransactionsLoadException {
        LOGGER.info("Hello from bnp loader...");
        List<TransactionDto> transactionsReaded = newArrayList();
        //
        ExcelReader reader = new ExcelReader(filesPath + FILE_NAME);
        List<Map<Integer, String>> transactionsFileContent = reader.getAllRowsContent(TRANSACTIONS_FIRST_ROW_INDEX);
        transactionsFileContent.forEach(content -> {
            if(content != null){
                TransactionDto transformedTransaction = fromContentMapToTransactionDto(content);
                if(transformedTransaction != null){
                    transactionsReaded.add(transformedTransaction);
                }
            }
        });

        //
        return transactionsReaded;
    }

    private TransactionDto fromContentMapToTransactionDto(Map<Integer, String> contentMap){
        TransactionDto transactionDto = new TransactionDto();
        if(contentMap.isEmpty()){
            return null;
        }

        LocalDateTime transactionDate = getLocalDateTimeFromString(contentMap.get(DATE_COLUMN_INDEX), DATE_PATTERN);
        transactionDto.setDate(transactionDate);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setLabel(contentMap.get(CATEGORY_COLUMN_INDEX));
        transactionDto.setCategory(categoryDto);

        transactionDto.setLabel(contentMap.get(LABEL_COLUMN_INDEX));

        BigDecimal transactionAmout = new BigDecimal(contentMap.get(AMOUNT_COLUMN_INDEX));
        transactionDto.setAmount(transactionAmout);

        return transactionDto;
    }

}
