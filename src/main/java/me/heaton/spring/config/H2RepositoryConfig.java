package me.heaton.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Profile("test")
@Configuration
@EnableJpaRepositories("me.heaton.db.repository")
public class H2RepositoryConfig {

  @Bean(destroyMethod = "shutdown")
  public EmbeddedDatabase dataSource() {
    return new EmbeddedDatabaseBuilder().
        setType(EmbeddedDatabaseType.H2).
        addScript("db-compatibility-mode.sql").
        addScript("db-schema.sql").
        build();
  }

}
