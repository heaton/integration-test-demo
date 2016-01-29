package me.heaton.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;

@Profile("h2")
@Configuration
@EnableJpaRepositories("me.heaton.spring.db")
public class H2RepositoryConfig extends DbRepositoryConfig {

  @Bean(destroyMethod = "shutdown")
  @Override
  public EmbeddedDatabase dataSource() {
    return new EmbeddedDatabaseBuilder().
        setType(EmbeddedDatabaseType.H2).
        addScript("db-compatibility-mode.sql").
        addScript("db-schema.sql").
        build();
  }

  @Bean
  @Override
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    return newEntityManagerFactory(Database.H2);
  }

}
