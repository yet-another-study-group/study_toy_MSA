package com.study.Loan.Service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoanRequest {
    private String email;
    private long bookId;
    private int quantity;
}
