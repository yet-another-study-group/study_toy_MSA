package com.example.mocktest.service;

import com.example.mocktest.exception.TestException;
import com.example.mocktest.response.TestRtnConsts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Value("${memberId}")
    private long memberId;
    @Value("${rentalQuantity}")
    private int rentalQuantity;
    @Value("${bookId}")
    private long bookId;
    @Value("${bookQuantity}")
    private int bookQuantity;

    public int getRentalQuantity(long memberId) {
        if (this.memberId == memberId) {
            return rentalQuantity;
        } else {
            throw new TestException(TestRtnConsts.ERR400);
        }
    }

    public int getBookQuantity(long bookId) {
        if (this.bookId == bookId) {
            return bookQuantity;
        } else {
            throw new TestException(TestRtnConsts.ERR401);
        }
    }
}

