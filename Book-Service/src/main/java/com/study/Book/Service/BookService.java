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
        exceptionCheck(bookId);
        int borrowedBooks = historyFeign.getBorrowedBookCount(bookId);
        int stock = findBookStock(bookId);
        return stock != borrowedBooks;
    }

    private void exceptionCheck(long bookId) {
        if (bookId == 0) {
            NullPointerException e = new NullPointerException();
            e.printStackTrace();
            log.error("NullPointerException ERROR: {}", e.getMessage());
            throw e;
        }
        else if (!bookRepository.existsById(bookId)) {
            EntityNotFoundException e = new EntityNotFoundException();
            e.printStackTrace();
            log.error("EntityNotFoundException: {}", e.getMessage());
            throw e;
        }
    }

    private int findBookStock(long bookId) {
        return bookRepository.findStockById(bookId);
    }
}