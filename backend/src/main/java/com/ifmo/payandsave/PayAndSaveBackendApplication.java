package com.ifmo.payandsave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PayAndSaveBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayAndSaveBackendApplication.class, args);
    }

}
