package me.heaton.restassured;

import com.google.gson.Gson;
import com.jayway.restassured.response.Response;
import me.heaton.spring.Application;
import me.heaton.spring.db.Book;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.sql.DataSource;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("h2")
@WebAppConfiguration
@SpringApplicationConfiguration(Application.class)
@IntegrationTest
public class RestAssuredBookSpec {

  @Autowired
  private DataSource dataSource;

  private Gson gson;

  @Before
  public void given_two_books() {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    jdbcTemplate.execute("TRUNCATE TABLE book");
    jdbcTemplate.execute("insert into Book (id, name, author, description) values (1, 'Clean Code', 'Robert C. Martin', 'A Handbook of Agile Software Craftsmanship')");
    jdbcTemplate.execute("insert into Book (id, name, author, description) values (2, 'The Clean Coder', 'Robert C. Martin', 'A Code of Conduct for Professional Programmers')");

    gson = new Gson();
  }

  @Test
  public void should_get_the_first_book() {
    expect()
        .body("name", equalTo("Clean Code"))
        .body("author", equalTo("Robert C. Martin"))
        .body("description", equalTo("A Handbook of Agile Software Craftsmanship"))
        .when().get("/books/1");
  }

  @Test
  public void should_get_all_books() {
    expect()
        .body("page.totalElements", equalTo(2))
        .when().get("/books");
  }

  @Test
  public void should_add_a_new_book() {
    Book book = new Book("The Three-Body Problem", "Liu Cixin", "Book series that set the foot stone of Chinese science fiction");
    final Response response = given()
        .request().contentType(JSON).body(gson.toJson(book))
        .when().post("/books")
        .then()
        .statusCode(HttpStatus.SC_CREATED)
        .and().extract().response();

    expect()
        .body("name", equalTo(book.getName()))
        .body("author", equalTo(book.getAuthor()))
        .body("description", equalTo(book.getDescription()))
        .when().get(response.getHeader("LOCATION"));
  }

  @Test
  public void should_modify_a_book() {
    given()
        .request().contentType(JSON).body("{ \"name\": \"New Clean Code\" }")
        .when().patch("/books/1")
        .then().statusCode(HttpStatus.SC_OK);
    expect()
        .body("name", equalTo("New Clean Code"))
        .when().get("/books/1");
  }

  @Test
  public void should_delete_a_new_book() {
    expect().statusCode(HttpStatus.SC_NO_CONTENT)
        .when().delete("/books/1");
  }

}
