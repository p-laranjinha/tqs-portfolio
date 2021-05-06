package ua.plaranjinha.tqs_hw.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import ua.plaranjinha.tqs_hw.TqsHwApplication;

@org.springframework.context.annotation.Configuration
public class Configs {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build(); // Can add configs later (like timeouts)
    }

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(TqsHwApplication.class);
    }

}
