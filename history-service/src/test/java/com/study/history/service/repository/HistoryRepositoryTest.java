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
        List<History> UserRentalRecords = historyRepository.findAllByUserId(2L);
        // then
        assertThat(UserRentalRecords).isNotNull();
        assertThat(UserRentalRecords).hasSize(2);
        assertThat(UserRentalRecords.get(0).getBookId()).isEqualTo(2L);
        assertThat(UserRentalRecords.get(1).getQuantity()).isEqualTo(1);
        assertThat(UserRentalRecords.get(1).getQuantity()).isEqualTo(1L);
        assertThat(UserRentalRecords.get(1).getQuantity()).isEqualTo(1);
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