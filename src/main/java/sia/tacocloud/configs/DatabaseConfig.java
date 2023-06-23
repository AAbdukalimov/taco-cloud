//package sia.tacocloud.configs;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.transaction.PlatformTransactionManager;
//import javax.sql.DataSource;
//
//@Configuration
//@PropertySource("/.env")
//public class DatabaseConfig {
//
//    @Value("${spring.datasource.username}")
//    private String dbUsername;
//    @Value("${spring.datasource.password}")
//    private String dbPassword;
//
//    @Bean
//    public DataSource dataSource() {
//        // Настройка свойств базы данных
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/tacocloud");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("postgres");
//        return dataSource;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }
//
//}
