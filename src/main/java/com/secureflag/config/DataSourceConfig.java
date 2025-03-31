package com.secureflag.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    public final static String JDBC_H2_URL = "jdbc:h2:mem:secureflag_event_service;DB_CLOSE_DELAY=-1;MODE=MySQL";
    public final static String DRIVER_CLASS_NAME = "org.h2.Driver";

    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource dataSource() throws JsonProcessingException {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(DRIVER_CLASS_NAME);
        dataSourceBuilder.url(JDBC_H2_URL);
        return dataSourceBuilder.build();
    }
}
