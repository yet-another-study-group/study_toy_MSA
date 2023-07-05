package com.study.Book.Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Transactional
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
        long bookId = 2;
        when(historyFeign.getBookQuantity(bookId)).thenReturn(2);
        when(bookRepository.findStockById(bookId)).thenReturn(5);
        when(bookRepository.existsById(bookId)).thenReturn(true);

        boolean pass = bookService.verify(bookId);

        assertThat(pass).isEqualTo(true);
    }

    @Test
    @DisplayName("책 대여 가능 여부 실패")
    void test2() {
        long bookId = 2;
        when(historyFeign.getBookQuantity(bookId)).thenReturn(5);
        when(bookRepository.findStockById(bookId)).thenReturn(5);
        when(bookRepository.existsById(bookId)).thenReturn(true);

        boolean pass = bookService.verify(bookId);

        assertThat(pass).isEqualTo(false);
    }

    @Test
    @DisplayName("존재하지 않는 BookId")
    void test3() {
        when(bookRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
            bookService.verify(anyLong());
        });
    }
}