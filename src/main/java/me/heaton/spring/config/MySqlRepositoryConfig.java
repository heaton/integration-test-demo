package me.heaton.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;

import javax.sql.DataSource;

@Profile("mysql")
@Configuration
@EnableJpaRepositories("me.heaton.spring.db")
public class MySqlRepositoryConfig extends DbRepositoryConfig{

  @Bean
  @Override
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost:3306/spring_testing");
    dataSource.setUsername("root");
    return dataSource;
  }

  @Bean
  @Override
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    return newEntityManagerFactory(Database.MYSQL);
  }

}
