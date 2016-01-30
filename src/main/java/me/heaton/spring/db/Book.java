package me.heaton.spring.db;

import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Data
@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private String author;
  private String description;

  @Tolerate
  public Book(String name, String author, String description) {
    this.name = name;
    this.author = author;
    this.description = description;
  }

}
