package io.microsamples.rmi.chachkiesserver;

import io.microsamples.rmi.Chachkie;
import io.microsamples.rmi.ChachkieService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@SpringBootTest
class ChachkiesServerApplicationTests {

	@Bean
	RmiProxyFactoryBean rmiProxy() {
		RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
		bean.setServiceInterface(ChachkieService.class);
		bean.setServiceUrl("rmi://localhost:1099/ChachkieService");

		return bean;
	}

	@Test
	void contextLoads() {
		String[] args = {};
		ChachkieService service = SpringApplication
				.run(ChachkieService.class, args).getBean(ChachkieService.class);
		Chachkie bookingOutcome = service.whereIsMyChachkie();
		System.out.println(bookingOutcome);
	}

}
