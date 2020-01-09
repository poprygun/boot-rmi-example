package io.microsamples.rmi.chachkiesserver;

import io.microsamples.rmi.ChachkieService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

@SpringBootApplication
public class ChachkiesServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChachkiesServerApplication.class, args);
    }

    @Bean
    RmiServiceExporter exporter(ChachkieService implementation) {

        // Expose a service via RMI. Remote obect URL is:
        // rmi://<HOST>:<PORT>/<SERVICE_NAME>
        // 1099 is the default port

        Class<ChachkieService> serviceInterface = ChachkieService.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(implementation);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(1099);
        return exporter;
    }

    @Bean
    ChachkieService bookingService() {
        return new ChachkieServiceImpl();
    }
}


