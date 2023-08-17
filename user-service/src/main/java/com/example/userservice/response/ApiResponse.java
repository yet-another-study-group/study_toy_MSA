package com.example.userservice.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private final String message;
    private final int code;
    private final T data;

    public static <T> ApiResponse<T> create() {
        return new ApiResponse<> (
                "생성 완료",
                201,
                null
        );
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<> (
                "성공",
                200,
                data
        );
    }

    public static <T> ApiResponse<T> failure(String message) {
        return new ApiResponse<>(
                message,
                400,
                null
        );
    }

}
