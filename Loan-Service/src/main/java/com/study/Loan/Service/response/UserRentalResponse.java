package com.study.Loan.Service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRentalResponse {
    private AvailabilityStatus rentalRight;
    private long userId;
}
