package com.study.Book.Service;

import com.study.Book.Service.entity.Book;
import com.study.Book.Service.feign.HistoryFeign;
import com.study.Book.Service.repository.BookRepository;
import com.study.Book.Service.response.BookAvailabilityStatus;
import com.study.Book.Service.response.BookStockRecord;
import com.study.Book.Service.response.BookStockResponse;
import com.study.Book.Service.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @InjectMocks
    BookService bookService;
    @Mock
    BookRepository bookRepository;
    @Mock
    HistoryFeign historyFeign;

    @Test
    @DisplayName("책 대여가 가능한 경우")
    public void testBookAvailable() {
        // given
        long bookId = 1L;
        int stock = 5;
        int quantity = 2;

        Book book = new Book();
        book.setId(bookId);
        book.setStock(stock);

        List<BookStockRecord> stockRecords = Collections.singletonList(new BookStockRecord(1L, quantity));

        BookStockResponse stockResponse = mock(BookStockResponse.class);
        when(stockResponse.getBookStockRecords()).thenReturn(stockRecords);
        when(historyFeign.getBorrowedBookStock(bookId)).thenReturn(stockResponse);
        when(bookRepository.findById(bookId)).thenReturn(java.util.Optional.of(book));
        // when
        BookAvailabilityStatus result = bookService.checkBookAvailabilityForRental(bookId);
        // then
        assertEquals(BookAvailabilityStatus.AVAILABLE, result);
        verify(bookRepository).findById(bookId);
        verify(historyFeign).getBorrowedBookStock(bookId);
    }

    @Test
    @DisplayName("책 대여가 불가능한 경우")
    public void testBookNotAvailable() {
        // given
        long bookId = 1L;
        int stock = 5;
        int quantity = 5;

        Book book = new Book();
        book.setId(bookId);
        book.setStock(stock);

        List<BookStockRecord> stockRecords = Collections.singletonList(new BookStockRecord(1L, quantity));

        BookStockResponse stockResponse = mock(BookStockResponse.class);
        when(stockResponse.getBookStockRecords()).thenReturn(stockRecords);
        when(historyFeign.getBorrowedBookStock(bookId)).thenReturn(stockResponse);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        // when
        BookAvailabilityStatus result = bookService.checkBookAvailabilityForRental(bookId);
        // then
        assertEquals(BookAvailabilityStatus.NOT_AVAILABLE, result);
        verify(bookRepository).findById(bookId);
        verify(historyFeign).getBorrowedBookStock(bookId);
    }

    @Test
    @DisplayName("책의 재고를 반환하는 경우")
    public void testGetBookStock() {
        // given
        long bookId = 1L;
        int stock = 5;

        Book book = new Book();
        book.setId(bookId);
        book.setStock(stock);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        //when
        int bookStock = bookService.getTotalBookStock(bookId);
        //then
        assertEquals(stock, bookStock);
        verify(bookRepository).findById(bookId);
    }

    @Test
    @DisplayName("bookId를 못 찾는 경우 예외를 던지는지 확인")
    public void testBookStockNotFound() {
        // given
        long nonExistentBookId = 9L;
        when(bookRepository.findById(nonExistentBookId)).thenReturn(Optional.empty());
        // when, then
        assertThrows(EntityNotFoundException.class, () -> {
            bookService.getTotalBookStock(nonExistentBookId);
        });
        verify(bookRepository).findById(nonExistentBookId);
    }
}