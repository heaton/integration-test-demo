package me.heaton.spring.db;

import javax.persistence.*;

@Entity
public class Book {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String name;

  @Column
  private String author;

  @Column
  private String description;
}
