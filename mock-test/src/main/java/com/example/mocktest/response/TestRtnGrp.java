package com.example.mocktest.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TestRtnGrp {
    Success(200),
    Validation(400);
    private int statsCode;
}

