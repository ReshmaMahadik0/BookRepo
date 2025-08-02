package com.example.springbootone2many.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springbootone2many.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {


}
