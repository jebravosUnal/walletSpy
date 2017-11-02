package com.wallet;

import com.wallet.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandLineApp implements CommandLineRunner {

    @Autowired
    private MenuService menuService;

    public static void main(String[] args) {
        SpringApplication.run(CommandLineApp.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        menuService.printMenu();
    }
}
