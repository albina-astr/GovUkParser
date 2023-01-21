package com.tool.govukparser;

import com.tool.govukparser.service.GovUkParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Albina Gimaletdinova on 17/01/2023
 */
@SpringBootApplication
public class GovUkParserApplication implements CommandLineRunner {
    @Autowired
    private GovUkParserService service;

    public static void main(String[] args) {
        SpringApplication.run(GovUkParserApplication.class, args);
    }

    @Override
    public void run(String... args) {
        service.parseAndStoreData();
    }
}
