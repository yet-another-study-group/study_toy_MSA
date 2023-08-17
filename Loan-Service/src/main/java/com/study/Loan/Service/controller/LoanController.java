package com.study.Loan.Service.controller;

import com.study.Loan.Service.response.LoanRequest;
import com.study.Loan.Service.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @PostMapping("/loan/request")
    public ResponseEntity<String> processLoanRequest(@RequestBody LoanRequest request) {
        String result = loanService.processLoanRequest(request.getEmail(), request.getBookId(), request.getQuantity());
        return ResponseEntity.ok(result);
    }
}


