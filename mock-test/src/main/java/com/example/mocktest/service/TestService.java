package com.example.mocktest.service;

import com.example.mocktest.exception.TestException;
import com.example.mocktest.response.TestRtnConsts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestService {
    @Value("${memberId}")
    private long memberId;
    @Value("${quantity}")
    private int quantity;
    @Value("${bookIds}")
    private List<Long> bookIds;
    @Value("${bookId}")
    private Long bookId;
    @Value("${stock}")
    private int stock;

    public Map<String, Object> getRentalQuantity(long memberId) {
        if (this.memberId != memberId) throw new TestException(TestRtnConsts.ERR400);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("memberId", this.memberId);
        response.put("quantity", this.quantity);
        response.put("bookId", bookIds);
        return response;
        }

    public Map<String, Object> getBookQuantity(long bookId) {
        if (this.bookId != bookId) throw new TestException(TestRtnConsts.ERR401);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("bookId", this.bookId);
        response.put("stock", this.stock);
        return response;
        }
    }


