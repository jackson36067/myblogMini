package com.jackson.myblogminisystem.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.jackson.myblogminisystem.repository")
@EntityScan(basePackages = "com.jackson.dao")
public class JpaConfig {
}
