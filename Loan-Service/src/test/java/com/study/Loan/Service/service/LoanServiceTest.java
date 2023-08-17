package com.study.Loan.Service.service;

import com.study.Loan.Service.feign.BookFeign;
import com.study.Loan.Service.feign.HistoryFeign;
import com.study.Loan.Service.feign.UserFeign;
import com.study.Loan.Service.response.AvailabilityStatus;
import com.study.Loan.Service.response.UserRentalResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {
    @Mock
    private BookFeign bookFeign;
    @Mock
    private HistoryFeign historyFeign;
    @Mock
    private UserFeign userFeign;
    @InjectMocks
    private LoanService loanService;

    @Test
    @DisplayName("대여가 가능한 경우")
    public void whenBookAndUserAvailable() {
        // given
        long bookId = 1L;
        int quantity = 1;
        String email = "test@gmail.com";
        UserRentalResponse userResponse = new UserRentalResponse(AvailabilityStatus.AVAILABLE, 1L);

        given(bookFeign.getBookRentalStatus(bookId)).willReturn(AvailabilityStatus.AVAILABLE);
        given(userFeign.getUserRentalStatus(email)).willReturn(userResponse);
        // when
        String result = loanService.processLoanRequest(email, bookId, quantity);
        // then
        assertEquals("대여 완료", result);
        verify(historyFeign, times(1)).saveLoanRecord(userResponse.getUserId(), bookId, quantity);
    }

    @Test
    @DisplayName("책과 유저 둘다 불가능한 경우")
    public void whenBookAndUserNotAvailable() {
        // given
        long bookId = 1L;
        int quantity = 1;
        String email = "test@gmail.com";
        UserRentalResponse userResponse = new UserRentalResponse(AvailabilityStatus.NOT_AVAILABLE, 1L);

        given(bookFeign.getBookRentalStatus(bookId)).willReturn(AvailabilityStatus.NOT_AVAILABLE);
        given(userFeign.getUserRentalStatus(email)).willReturn(userResponse);
        // when
        String result = loanService.processLoanRequest(email, bookId, quantity);
        // then
        assertEquals("대여 불가능", result);
        verify(historyFeign, never()).saveLoanRecord(anyLong(), anyLong(), anyInt());

    }

    @Test
    @DisplayName("책이 불가능한 경우")
    public void whenBookNotAvailable() {
        // given
        long bookId = 1L;
        int quantity = 1;
        String email = "test@gmail.com";

        given(bookFeign.getBookRentalStatus(bookId)).willReturn(AvailabilityStatus.NOT_AVAILABLE);
        // when
        String result = loanService.processLoanRequest(email, bookId, quantity);
        // then
        assertEquals("대여 불가능", result);
        verify(historyFeign, never()).saveLoanRecord(anyLong(), anyLong(), anyInt());
    }

    @Test
    @DisplayName("유저가 불가능한 경우")
    public void whenUserNotAvailable() {
        // given
        long bookId = 1L;
        int quantity = 1;
        String email = "test@gmail.com";
        UserRentalResponse userResponse = new UserRentalResponse(AvailabilityStatus.NOT_AVAILABLE, 1L);

        given(bookFeign.getBookRentalStatus(bookId)).willReturn(AvailabilityStatus.AVAILABLE);
        given(userFeign.getUserRentalStatus(email)).willReturn(userResponse);
        // when
        String result = loanService.processLoanRequest(email, bookId, quantity);
        // then
        assertEquals("대여 불가능", result);
        verify(historyFeign, never()).saveLoanRecord(anyLong(), anyLong(), anyInt());
    }
}