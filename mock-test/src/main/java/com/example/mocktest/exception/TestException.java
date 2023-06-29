package com.example.mocktest.exception;

import com.example.mocktest.response.TestRtnConsts;
import lombok.Getter;

@Getter
public class TestException extends RuntimeException {
    private final TestRtnConsts testRtnConsts;

    public TestException(TestRtnConsts testRtnConsts) {
        super(testRtnConsts.getDescription());
        this.testRtnConsts = testRtnConsts;
    }
}

