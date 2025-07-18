package com.secureflag.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DefaultLiquibaseConfig {

    private final DataSource dataSource;

    public DefaultLiquibaseConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Value("${spring.liquibase.change-log:classpath:db/changelog.xml}")
    private String CHANGE_LOG;

    @Value("${spring.liquibase.enabled:true}")
    private boolean SHOULD_RUN;

    @Bean
    @Primary
    @Profile({"default", "dev"})
    public SpringLiquibase liquibase() {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog(CHANGE_LOG);
        springLiquibase.setShouldRun(SHOULD_RUN);
        return springLiquibase;
    }
}
