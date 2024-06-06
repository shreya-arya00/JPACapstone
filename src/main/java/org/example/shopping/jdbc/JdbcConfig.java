package org.example.shopping.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

import java.sql.SQLException;

@Configuration
public class JdbcConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/skumari")
                .username("skumari")
                .password("your_password")
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    @Bean
    public ProductDAO productDAO(DataSource dataSource) throws SQLException {
        return new ProductDAO(dataSource.getConnection());
    }
}
