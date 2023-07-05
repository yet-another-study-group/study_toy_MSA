package com.example.mocktest.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {
    @JsonIgnore
    private final TestRtnConsts status;
    private final String message;
    private final int code;
    private final T data;

    public static <T> ApiResponse<T> notContent(TestRtnConsts status, String message) {

        return new ApiResponse<>(
                status,
                message,
                status.getCode(),
                null
        );
    }
}
