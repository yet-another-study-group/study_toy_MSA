package com.study.Book.Service.controller;

import com.study.Book.Service.response.BookAvailabilityStatus;
import com.study.Book.Service.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/book/verify/{bookId}")
    public ResponseEntity<BookAvailabilityStatus> checkBookAvailability(@PathVariable long bookId) {
        return ResponseEntity.ok(bookService.checkBookAvailabilityForRental(bookId));
    }
}

