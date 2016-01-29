package me.heaton.spring;

import me.heaton.spring.config.H2RepositoryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(H2RepositoryConfig.class)
public class Application {

    public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
    }

}
