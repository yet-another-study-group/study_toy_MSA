package com.study.history.service.service;

import com.study.history.service.domain.History;
import com.study.history.service.repository.HistoryRepository;
import com.study.history.service.response.RentalRecord;
import com.study.history.service.response.RentalResponse;
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
    void shouldReturnRentalRecordsForUserId() {
        //given
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
        //when
        RentalResponse rentalResponse = historyService.getRentalRecords(userId);

        //then
        assertThat(rentalResponse).isNotNull();
        List<RentalRecord> rentalRecords = rentalResponse.getUserRentalRecords();
        assertThat(rentalRecords).hasSize(2);
        assertThat(rentalRecords.get(0).getBookId()).isEqualTo(1);
        assertThat(rentalRecords.get(0).getQuantity()).isEqualTo(2);
        assertThat(rentalRecords.get(1).getBookId()).isEqualTo(2);
        assertThat(rentalRecords.get(1).getQuantity()).isEqualTo(2);
    }
}