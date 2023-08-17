package com.example.mocktest.controller;

import com.example.mocktest.response.ApiResponse;
import com.example.mocktest.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {
    private final TestService testService;

    @GetMapping("/test/member/{memberId}")
    public ApiResponse<Map<String, Object>> getMemberQuantity(@PathVariable long memberId) {
        return ApiResponse.ok(testService.getRentalQuantity(memberId));
    }

    @GetMapping("/test/book/{bookId}")
    public ApiResponse<Map<String, Object>> getBookQuantity(@PathVariable long bookId) {
        return ApiResponse.ok(testService.getBookQuantity(bookId));
    }
}
