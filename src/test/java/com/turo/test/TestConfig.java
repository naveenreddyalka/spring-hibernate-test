package com.turo.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.turo"})
@EnableJpaRepositories(
    entityManagerFactoryRef = "serversEntityManager",
    transactionManagerRef = "serversTransactionManager",
    basePackages = {"com.turo"})
public class TestConfig {}
