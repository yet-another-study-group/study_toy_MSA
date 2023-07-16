package com.study.history.service.service;

import com.study.history.service.domain.History;
import com.study.history.service.repository.HistoryRepository;
import com.study.history.service.response.RentalRecord;
import com.study.history.service.response.RentalResponse;
import com.study.history.service.response.StockRecord;
import com.study.history.service.response.StockResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HistoryServiceTest {
    @Mock
    HistoryRepository historyRepository;
    @InjectMocks
    HistoryService historyService;

    @Test
    @DisplayName("getRentalRecords() 테스트")
    void getRentalRecordsTest() {
        // given
        long userId = 1L;
        List<History> testDataList = new ArrayList<>();
        testDataList.add(new History(1, 1, 2));
        testDataList.add(new History(1, 2, 2));
        testDataList.add(new History(2, 3, 1));
        testDataList.add(new History(3, 4, 1));
        when(historyRepository.findAllByUserId(userId)).thenReturn(
                testDataList.stream()
                        .filter(history -> history.getUserId() == userId)
                        .collect(Collectors.toList())
        );
        // when
        RentalResponse rentalResponse = historyService.getRentalRecords(userId);
        // then
        assertThat(rentalResponse).isNotNull();
        List<RentalRecord> rentalRecords = rentalResponse.getUserRentalRecords();
        assertThat(rentalRecords).hasSize(2);
        assertThat(rentalRecords.get(0).getBookId()).isEqualTo(1L);
        assertThat(rentalRecords.get(0).getQuantity()).isEqualTo(2);
        assertThat(rentalRecords.get(1).getBookId()).isEqualTo(2L);
        assertThat(rentalRecords.get(1).getQuantity()).isEqualTo(2);
    }

    @Test
    @DisplayName("getStockRecords() 테스트")
    void getStockRecordsTest() {
        // given
        long bookId = 2L;
        List<History> testDataList = new ArrayList<>();
        testDataList.add(new History(2, 2, 1));
        testDataList.add(new History(1, 2, 2));
        testDataList.add(new History(3, 1, 1));
        testDataList.add(new History(2, 3, 1));
        when(historyRepository.findAllByBookId(bookId)).thenReturn(
                testDataList.stream().filter(history -> history.getBookId() == bookId)
                        .collect(Collectors.toList())
        );
        // when
        StockResponse stockResponse = historyService.getStockRecords(bookId);
        // then
        assertThat(stockResponse ).isNotNull();
        List<StockRecord> stockRecords = stockResponse.getBookStockRecords();
        assertThat(stockRecords).hasSize(2);
        assertThat(stockRecords.get(0).getUserId()).isEqualTo(2L);
        assertThat(stockRecords.get(0).getQuantity()).isEqualTo(1);
        assertThat(stockRecords.get(1).getUserId()).isEqualTo(1L);
        assertThat(stockRecords.get(1).getQuantity()).isEqualTo(2);
    }
}