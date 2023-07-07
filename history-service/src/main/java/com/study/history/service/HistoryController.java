package com.study.history.service;

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

    @GetMapping("/histories/{userId}")
    public ResponseEntity<RentalResponse> sendRentalRecords(@PathVariable long userId) {
        RentalResponse rentalRecords = historyService.getRentalRecords(userId);
        return ResponseEntity.ok(rentalRecords);
    }
}
