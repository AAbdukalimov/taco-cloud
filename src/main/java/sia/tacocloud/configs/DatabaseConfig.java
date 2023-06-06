package sia.tacocloud.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        // Настройка свойств базы данных
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/tacocloud");
        dataSource.setUsername("postgresqluser");
        dataSource.setPassword("postgresqluser");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
