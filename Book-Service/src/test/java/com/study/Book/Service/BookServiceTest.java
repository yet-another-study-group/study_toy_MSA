package com.study.Book.Service;

import com.study.Book.Service.feign.HistoryFeign;
import com.study.Book.Service.repository.BookRepository;
import com.study.Book.Service.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Mock
    HistoryFeign historyFeign;

    @Test
    @DisplayName("책 대여 가능 여부 성공")
    void test1() {
        when(historyFeign.getBorrowedBookCount(anyLong())).thenReturn(2);
        when(bookRepository.findStockById(anyLong())).thenReturn(5);
        when(bookRepository.existsById(anyLong())).thenReturn(true);

        boolean pass = bookService.verify(anyLong());

        assertThat(pass).isEqualTo(true);
    }

    @Test
    @DisplayName("책 대여 가능 여부 성공2")
    void test1_2() {
        when(historyFeign.getBorrowedBookCount(anyLong())).thenReturn(4);
        when(bookRepository.findStockById(anyLong())).thenReturn(5);
        when(bookRepository.existsById(anyLong())).thenReturn(true);

        boolean pass = bookService.verify(anyLong());

        assertThat(pass).isEqualTo(true);
    }

    @Test
    @DisplayName("책 대여 가능 여부 실패")
    void test2() {
        when(historyFeign.getBorrowedBookCount(anyLong())).thenReturn(5);
        when(bookRepository.findStockById(anyLong())).thenReturn(5);
        when(bookRepository.existsById(anyLong())).thenReturn(true);

        boolean pass = bookService.verify(anyLong());

        assertThat(pass).isEqualTo(false);
    }

    @Test
    @DisplayName("존재하지 않는 BookId")
    void test3() {
        when(bookRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            bookService.verify(anyLong());
        });
    }
}