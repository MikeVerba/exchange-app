package com.example.exchangeapp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "currency-api")
public class ApiProperties {
    private String baseUrl;
    private String exchangeGroup;
    private String formatVariable;
}
