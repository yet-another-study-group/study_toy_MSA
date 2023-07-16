package com.study.history.service.repository;

import com.study.history.service.domain.History;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class HistoryRepositoryTest {
    @Autowired
    private HistoryRepository historyRepository;

    @Test
    @DisplayName("저장된 userId 기록이 제대로 조회되는지 확인")
    void findAllByUserIdTest() {
        // when
        List<History> userRentalRecords = historyRepository.findAllByUserId(2L);
        // then
        assertThat(userRentalRecords).isNotNull();
        assertThat(userRentalRecords).hasSize(2);
        assertThat(userRentalRecords.get(0).getBookId()).isEqualTo(2L);
        assertThat(userRentalRecords.get(0).getQuantity()).isEqualTo(1);
        assertThat(userRentalRecords.get(1).getBookId()).isEqualTo(1L);
        assertThat(userRentalRecords.get(1).getQuantity()).isEqualTo(1);
    }

    @Test
    @DisplayName("저장된 bookId 기록이 제대로 조회되는지 확인")
    void findAllByBookIdTest() {
        // when
        List<History> bookStockRecords = historyRepository.findAllByBookId(1L);
        // then
        assertThat(bookStockRecords).isNotNull();
        assertThat(bookStockRecords).hasSize(2);
        assertThat(bookStockRecords.get(0).getUserId()).isEqualTo(1L);
        assertThat(bookStockRecords.get(0).getQuantity()).isEqualTo(1);
        assertThat(bookStockRecords.get(1).getUserId()).isEqualTo(2L);
        assertThat(bookStockRecords.get(1).getQuantity()).isEqualTo(1);
    }

    @Test
    @DisplayName("데이터가 DB에 저장이 잘 되는지 확인")
    void saveHistoryTest() {
        // given
        History history = new History(3L, 5L, 1);
        // When
        History savedHistory = historyRepository.save(history);
        // then
        assertThat(savedHistory).isNotNull();
        assertThat(savedHistory.getUserId()).isEqualTo(3L);
        assertThat(savedHistory.getBookId()).isEqualTo(5L);
        assertThat(savedHistory.getQuantity()).isEqualTo(1);
    }

    @Test
    @DisplayName("여러 데이터를 한번에 저장할 경우 DB에 저장이 잘 되는지 확인")
    void SaveAllHistoryTest() {
        // given
        List<History> histories = List.of(
                new History(3L, 1L, 1),
                new History(4L, 4L, 2)
        );
        // when
        List<History> savedHistories = historyRepository.saveAll(histories);
        // then
        assertThat(savedHistories)
                .isNotNull()
                .hasSameSizeAs(histories)
                .containsExactlyElementsOf(histories);
    }
}