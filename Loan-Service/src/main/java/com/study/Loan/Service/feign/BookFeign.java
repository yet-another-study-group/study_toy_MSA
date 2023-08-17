package com.study.Loan.Service.feign;

import com.study.Loan.Service.response.AvailabilityStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "BookFeign", url = "http://localhost:8082/api")
public interface BookFeign {
    @GetMapping("/book/verify/{bookId}")
    AvailabilityStatus getBookRentalStatus(@PathVariable("bookId") long bookId);
}
