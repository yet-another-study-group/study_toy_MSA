package com.study.Loan.Service.feign;

import com.study.Loan.Service.response.UserRentalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "UserFeign", url = "http://localhost:8085")
public interface UserFeign {
    @GetMapping("/user/rentalRight/{userEmail}")
    UserRentalResponse getUserRentalStatus(@PathVariable("userEmail") String userEmail);
}
