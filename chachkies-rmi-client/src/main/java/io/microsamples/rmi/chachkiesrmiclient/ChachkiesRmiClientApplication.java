package io.microsamples.rmi.chachkiesrmiclient;

import io.microsamples.rmi.Chachkie;
import io.microsamples.rmi.ChachkieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@SpringBootApplication
@Slf4j
public class ChachkiesRmiClientApplication {

    @Bean
    RmiProxyFactoryBean service() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://localhost:1099/ChachkieService");
        rmiProxyFactory.setServiceInterface(ChachkieService.class);
        return rmiProxyFactory;
    }

    public static void main(String[] args) {
		final ChachkieService chachkieService = SpringApplication
				.run(ChachkiesRmiClientApplication.class, args).getBean(ChachkieService.class);

		final Chachkie chachkie = chachkieService.whereIsMyChachkie();
		log.info("Remote chachkie {}", chachkie);
	}

}
