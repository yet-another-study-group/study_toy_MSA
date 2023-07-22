package com.study.Book.Service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BookStockResponse {
    private List<BookStockRecord> bookStockRecords;

    public BookStockResponse() {
    }
}

