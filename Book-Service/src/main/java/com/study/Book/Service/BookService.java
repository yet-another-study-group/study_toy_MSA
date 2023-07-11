package com.study.Book.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final HistoryFeign historyFeign;

    @Transactional
    public boolean verify(long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new EntityNotFoundException("book not found with id");
        }
        int borrowedBooks = historyFeign.getBorrowedBookCount(bookId);
        int stock = bookRepository.findStockById(bookId);
        return stock > borrowedBooks;
    }
}