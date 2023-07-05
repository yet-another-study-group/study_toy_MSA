package com.example.mocktest.controller;

import com.example.mocktest.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {
    private final TestService testService;

    @GetMapping("/test/member/{memberId}")
    public int getMemberQuantity(@PathVariable long memberId) {
        return testService.getRentalQuantity(memberId);
    }

    @GetMapping("/test/book/{bookId}")
    public int getBookQuantity(@PathVariable long bookId) {
        return testService.getBookQuantity(bookId);
    }
}
