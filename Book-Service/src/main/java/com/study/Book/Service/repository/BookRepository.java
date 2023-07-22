package com.study.Book.Service.repository;

import com.study.Book.Service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b.stock FROM Book b WHERE b.id = :bookId")
    int findStockById(long bookId);
}

