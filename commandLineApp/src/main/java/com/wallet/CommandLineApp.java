package com.wallet;

import com.wallet.menu.Menu;
import com.wallet.menu.impl.InitialMenu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandLineApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CommandLineApp.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Hello World");
        System.out.println(strings);
        Menu initialMenu  = new InitialMenu();
        initialMenu.printMenu();
    }
}
