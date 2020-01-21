package io.microsamples.rmi.chachkiesserver;

import io.microsamples.rmi.ChachkieService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChachkiesServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(ChachkiesServerApplication.class, args);
    }

    @Bean
    ChachkieService chachkieService() {
        return new ChachkieServiceImpl();
    }
}


