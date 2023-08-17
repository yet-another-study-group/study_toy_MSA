package com.study.Loan.Service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "HistoryServiceFeign", url = "http://localhost:8083/api")
public interface HistoryFeign {
    @PostMapping("/histories/loan/save")
    void saveLoanRecord(@RequestParam long userId, @RequestParam long bookId, @RequestParam int quantity);
}