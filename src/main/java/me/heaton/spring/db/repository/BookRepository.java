package me.heaton.spring.db.repository;

import me.heaton.spring.db.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer>{
}
