package com.study.Book.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HistoryFeign", url = "http://localhost:8081/api")
public interface HistoryFeign {
    @GetMapping("/test/book/{bookId}")
    int getBorrowedBookCount(@PathVariable("bookId") long bookId);
}