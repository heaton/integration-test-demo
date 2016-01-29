package me.heaton.spring;

import me.heaton.spring.config.H2RepositoryConfig;
import me.heaton.spring.config.MySqlRepositoryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Import({MySqlRepositoryConfig.class, H2RepositoryConfig.class})
public class Application {

    public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
    }

}
