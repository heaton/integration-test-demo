package me.heaton.spring.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

public interface RepositoryConfig {

    DataSource dataSource();

    FactoryBean<EntityManagerFactory> entityManagerFactory();

    PlatformTransactionManager transactionManager();
}
