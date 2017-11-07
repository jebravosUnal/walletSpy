package com.wallet.service.impl;

import com.wallet.document.Category;
import com.wallet.entity.Transaction;
import com.wallet.exceptions.TransactionsLoadException;
import com.wallet.exceptions.WalletException;
import com.wallet.service.TransactionLoader;
import com.wallet.service.TransactionService;
import com.wallet.utils.ExcelReader;
import com.wallet.utils.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.google.common.collect.Lists.newArrayList;
import static com.wallet.utils.DateUtils.getLocalDateFromString;
import static com.wallet.utils.FileUtils.MINIMUN_LEVEL_DEEP;

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

    private static final String DATE_PATTERN = "dd-MM-yyyy";

    @Value("${wallet.loader.file.repository}")
    private String filesPath;
    @Value("${wallet.loader.file.transactionsTestFile}")
    private String testFile;


    @Autowired
    private TransactionService transactionService;

    @Override
    public void loadTransactions() throws TransactionsLoadException {
        LOGGER.warn("Not implemented...");
    }

    @Override
    public List<Transaction> loadAndGetTransactions() throws TransactionsLoadException {
        return loadFromPathAndGetTransactions(Paths.get(filesPath + testFile));
    }

    @Override
    public List<Transaction> loadFromDirectoryAndGetTransactions(String directory) throws WalletException {
        List<Transaction> transactionsLoaded = newArrayList();

        try {
            try(Stream<Path> files = FileUtils.streamFilesPath(directory, MINIMUN_LEVEL_DEEP)){
                       files.forEach(path -> {
                           transactionsLoaded.addAll(loadFromPathAndGetTransactions(path));
                       });
            }
        } catch (IOException e) {
            throw new WalletException("An error was ocurred reading files from directory " + directory);
        }

        return transactionsLoaded;
    }

    private List<Transaction> loadFromPathAndGetTransactions(Path path){
        ExcelReader reader = new ExcelReader(path.toString());
        LOGGER.info("Loading from  " + reader.getFileName());
        List<Transaction> transactionsRead = newArrayList();
        List<Transaction> transactionsLoaded = newArrayList();

        List<Map<Integer, String>> transactionsFileContent = reader.getAllRowsContent(TRANSACTIONS_FIRST_ROW_INDEX);
        transactionsFileContent.forEach(content -> {
            if (content != null) {
                Transaction transformedTransaction = fromContentMapToTransaction(content);
                if (transformedTransaction != null) {
                    transactionsRead.add(transformedTransaction);
                    transactionService.insertOrUpdateCategoryIfExists(transformedTransaction).ifPresent(transaction -> transactionsLoaded.add(transaction));

                }
            }
        });

        reader.close();
        //
        return transactionsLoaded;
    }

    private Transaction fromContentMapToTransaction(Map<Integer, String> contentMap) {
        Transaction transaction = new Transaction();
        if (contentMap.isEmpty()) {
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
