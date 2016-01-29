package me.heaton.spring.db;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private String author;

  private String description;

  public Book() {}

  public Book(String name, String author, String description) {
    this.name = name;
    this.author = author;
    this.description = description;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAuthor() {
    return author;
  }

  public String getDescription() {
    return description;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return Objects.equal(id, book.id) &&
        Objects.equal(name, book.name) &&
        Objects.equal(author, book.author) &&
        Objects.equal(description, book.description);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, name, author, description);
  }

  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", author='" + author + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
