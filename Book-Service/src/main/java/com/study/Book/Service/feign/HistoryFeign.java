package com.study.Book.Service.feign;

import com.study.Book.Service.response.BookStockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HistoryFeign", url = "http://localhost:8083/api")
public interface HistoryFeign {
    @GetMapping("/histories/book/{bookId}")
    BookStockResponse getBorrowedBookStock(@PathVariable("bookId") long bookId);
}