package com.study.Book.Service.service;

import com.study.Book.Service.response.BookAvailabilityStatus;
import com.study.Book.Service.entity.Book;
import com.study.Book.Service.feign.HistoryFeign;
import com.study.Book.Service.repository.BookRepository;
import com.study.Book.Service.response.BookStockRecord;
import com.study.Book.Service.response.BookStockResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    private final HistoryFeign historyFeign;

    public BookAvailabilityStatus checkBookAvailabilityForRental(long bookId) {
        int totalStock = getTotalBookStock(bookId);
        ResponseEntity<BookStockResponse> responseEntity = historyFeign.getBorrowedBookStock(bookId);
        List<BookStockRecord> stockRecords = responseEntity.getBody().getBookStockRecords();
        int rentedBook = stockRecords.stream()
                .mapToInt(BookStockRecord::getQuantity)
                .sum();

        if (totalStock - rentedBook <= 0) return BookAvailabilityStatus.NOT_AVAILABLE;
        return BookAvailabilityStatus.AVAILABLE;
    }

    public int getTotalBookStock(long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("ID가 " + bookId + "인 도서를 찾을 수 없습니다."));
        return book.getStock();
    }
}
