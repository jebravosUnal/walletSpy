package com.wallet;

import com.wallet.service.impl.TransactionServiceImpl;
import com.wallet.services.MenuService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandLineApp implements CommandLineRunner {

    private final static Logger LOGGER = LogManager.getLogger(CommandLineApp.class);

    @Autowired
    private MenuService menuService;

    public static void main(String[] args) {
        SpringApplication.run(CommandLineApp.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        LOGGER.debug("Here is some DEBUG");
        LOGGER.info("Here is some INFO");
        LOGGER.warn("Here is some WARN");
        LOGGER.error("Here is some ERROR");
        LOGGER.fatal("Here is some FATAL");
        menuService.printMenu();
    }
}
