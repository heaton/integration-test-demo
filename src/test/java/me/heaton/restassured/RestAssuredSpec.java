package me.heaton.restassured;

import me.heaton.spring.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
@IntegrationTest
public class RestAssuredSpec {

  @Test
  public void get_greeting() {
    expect().body("content", equalTo("Hello, World!")).when().get("/greeting");
  }

  @Test
  public void get_greeting_with_name() {
    given().queryParam("name", "Heaton")
      .when().get("/greeting")
      .then().body("content", equalTo("Hello, Heaton!"));
  }

}
