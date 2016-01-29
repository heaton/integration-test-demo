package me.heaton.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate4.SpringSessionContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Properties;

public abstract class DbRepositoryConfig implements RepositoryConfig{

  protected LocalContainerEntityManagerFactoryBean newEntityManagerFactory(Database db) {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setPackagesToScan("me.heaton.spring.db");
    entityManagerFactoryBean.setJpaProperties(new Properties() {{
      put("hibernate.current_session_context_class", SpringSessionContext.class.getName());
    }});
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {{
      setDatabase(db);
    }});
    return entityManagerFactoryBean;
  }

  @Bean
  @Override
  public PlatformTransactionManager transactionManager() {
    return new JpaTransactionManager();
  }

}
