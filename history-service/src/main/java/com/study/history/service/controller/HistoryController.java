package com.study.history.service.controller;

import com.study.history.service.response.RentalResponse;
import com.study.history.service.response.StockResponse;
import com.study.history.service.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/histories/user/{userId}")
    public ResponseEntity<RentalResponse> sendRentalRecords(@PathVariable long userId) {
        RentalResponse rentalRecords = historyService.getRentalRecords(userId);
        return ResponseEntity.ok(rentalRecords);
    }

    @GetMapping("/histories/book/{bookId}")
    public ResponseEntity<StockResponse> sendStockRecords(@PathVariable long bookId) {
        StockResponse stockRecords = historyService.getStockRecords(bookId);
        return ResponseEntity.ok(stockRecords);
    }
}
