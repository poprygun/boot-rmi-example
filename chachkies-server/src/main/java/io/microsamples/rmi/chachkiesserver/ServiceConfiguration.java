package io.microsamples.rmi.chachkiesserver;

import io.microsamples.rmi.ChachkieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class ServiceConfiguration {

    @Bean(name = "/chachkie")
    HttpInvokerServiceExporter accountService(ChachkieService chachkieService) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(chachkieService);
        exporter.setServiceInterface(ChachkieService.class);
        return exporter;
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
        return exporter;
    }
}
