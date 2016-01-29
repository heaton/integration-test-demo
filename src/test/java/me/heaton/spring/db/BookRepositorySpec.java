package me.heaton.spring.db;

import me.heaton.spring.db.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmbeddedDbConfig.class)
public class BookRepositorySpec {

  @Autowired
  private BookRepository repository;

  @Test
  public void it_should_find_the_saved_book() {
    Book book = new Book("Clean Code", "Robert C. Martin", "A Handbook of Agile Software Craftsmanship");
    Book savedBook = repository.save(book);
    assertThat(repository.findOne(savedBook.getId()), is(savedBook));
  }

}
