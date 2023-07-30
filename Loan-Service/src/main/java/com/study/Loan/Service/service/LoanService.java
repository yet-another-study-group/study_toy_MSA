package com.study.Loan.Service.service;

import com.study.Loan.Service.feign.BookFeign;
import com.study.Loan.Service.feign.HistoryFeign;
import com.study.Loan.Service.feign.UserFeign;
import com.study.Loan.Service.response.AvailabilityStatus;
import com.study.Loan.Service.response.UserRentalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final BookFeign bookFeign;
    private final HistoryFeign historyFeign;
    private final UserFeign userFeign;

    public String processLoanRequest(String email, long bookId, int quantity) {
        AvailabilityStatus bookStatus = bookFeign.getBookRentalStatus(bookId);
        UserRentalResponse userResponse = userFeign.getUserRentalStatus(email);

        if (bookStatus == AvailabilityStatus.AVAILABLE || userResponse.getRentalRight() == AvailabilityStatus.AVAILABLE) {
            historyFeign.saveLoanRecord(userResponse.getUserId(), bookId, quantity);
            return "대여 완료";
        } else {
            return "대여 불가능";
        }
    }
}

