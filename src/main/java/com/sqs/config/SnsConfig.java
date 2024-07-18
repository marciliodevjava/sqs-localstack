package com.sqs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

import java.net.URI;

@Configuration
public class SnsConfig {
    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
                .endpointOverride(URI.create("http://localhost:4566")) // Endpoint do LocalStack
                .region(Region.SA_EAST_1) // Região padrão para LocalStack
                .build();
    }
}
