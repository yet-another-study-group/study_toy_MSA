package com.example.userservice.historyApi;

import com.example.userservice.response.ApiResponse;
import com.example.userservice.response.HistoryApiResponse;
import com.example.userservice.response.HistoryData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@FeignClient(name = "history-service", url = "${feign.history-service-url}", primary = false)
public interface HistoryApi {
    @GetMapping(value = "/api/histories/user/{memberId}")
    HistoryApiResponse getUserHistory(@PathVariable("memberId") long memberId);
}
