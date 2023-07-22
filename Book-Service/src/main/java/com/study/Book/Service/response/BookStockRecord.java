package com.study.Book.Service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookStockRecord {
    private long userId;
    private int quantity;
}
