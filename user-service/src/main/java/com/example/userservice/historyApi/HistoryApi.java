package com.example.userservice.historyApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "history-service", url = "${feign.history-service-url}", primary = false)
public interface HistoryApi {
    @GetMapping(value = "/api/test/member/{memberId}")
    int getRentalBookQuantity(@PathVariable("memberId") long memberId);
}
