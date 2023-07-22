package com.study.Book.Service.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BookStockResponse {
    private List<BookStockRecord> bookStockRecords;
}

