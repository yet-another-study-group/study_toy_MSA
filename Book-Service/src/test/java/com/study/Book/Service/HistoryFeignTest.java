package com.study.Book.Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class HistoryFeignTest {

    private HistoryFeign historyFeign;

    @Test
    @DisplayName("openFeign 요청 성공")
    void test1() {
        int quantity = historyFeign.getBookQuantity(2);

        assertThat(quantity).isEqualTo(3);
    }
}