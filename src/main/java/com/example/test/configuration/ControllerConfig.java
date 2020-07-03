package com.example.test.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "path")
public class ControllerConfig {
    private String registerUri;
    @Value(value = "get-uri")
    private String getInfoUri;
    private String uri;
}
