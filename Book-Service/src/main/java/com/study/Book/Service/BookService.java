package com.study.Book.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final HistoryFeign historyFeign;

    @Transactional
    public boolean verify(long bookId) {
        exceptionCheck(bookId);
        int borrowedBooks = historyFeign.getBookQuantity(bookId);
        int stock = findBookStock(bookId);
        boolean pass = checkBookStock(borrowedBooks, stock);
        return pass;
    }


    private void exceptionCheck(long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new IllegalArgumentException();
        }
    }

    private int findBookStock(long bookId) {
        int stock = bookRepository.findStockById(bookId);
        return stock;
    }

    private boolean checkBookStock(int borrowedBooks, int stock) {
        if (borrowedBooks == stock ) {
            return false;
        }
        else {
            return true;
        }
    }
}
