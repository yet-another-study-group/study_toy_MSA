package com.study.Book.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/book/verify/{book-id}")
    public boolean checkBookStock(@PathVariable("book-id") long bookId) {
        boolean pass = bookService.verify(bookId);
        return pass;
    }
}
