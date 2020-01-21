package io.microsamples.rmi.chachkiesrmiclient;

import io.microsamples.rmi.Chachkie;
import io.microsamples.rmi.ChachkieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Slf4j
public class ChachkiesRmiClientApplication {

    @Value("${rmi.service.url:rmi://localhost:1099/ChachkieService}")
    private String rmiServiceUrl;

    @Value("${http-invoker.service.url:http://localhost:8080/chachkie}")
    private String httpInvokerServiceUrl;

    @Bean
    @Profile("!http-invoker")
    RmiProxyFactoryBean service() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl(rmiServiceUrl);
        rmiProxyFactory.setServiceInterface(ChachkieService.class);
        log.info("Serving chachie from RMI endpoint.");
        return rmiProxyFactory;
    }

    @Bean
    @Profile("http-invoker")
    public HttpInvokerProxyFactoryBean invoker() {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setServiceUrl(httpInvokerServiceUrl);
        invoker.setServiceInterface(ChachkieService.class);
        log.info("Serving chachie from HTTP Invoker.");
        return invoker;
    }

    public static void main(String[] args) {
        SpringApplication
                .run(ChachkiesRmiClientApplication.class, args);
    }
}

@Component
@Slf4j
class CommandLineAppStartupRunner implements CommandLineRunner {

    private ChachkieService chachkieService;

    public CommandLineAppStartupRunner(ChachkieService chachkieService) {
        this.chachkieService = chachkieService;
    }

    @Override
    public void run(String... args) {
        final Chachkie chachkie = chachkieService.whereIsMyChachkie();
        log.info("Remote chachkie {}", chachkie);
    }
}
