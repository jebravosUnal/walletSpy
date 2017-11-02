//package com.wallet;
//
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
//
///**
// * Created by EBR3556 on 13/06/2017.
// */
//@SpringBootApplication
//public class WalletApp extends SpringBootServletInitializer {
//
//    private final static Logger LOGGER = LogManager.getLogger(WalletApp.class);
//
//    private static Class<WalletApp> applicationClass = WalletApp.class;
//
//    public static void main(String[] args) {
//        LOGGER.debug("@@@@Hola mundo desde wallet appp");
//        SpringApplication.run(WalletApp.class, args);
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(applicationClass);
//    }
//}
