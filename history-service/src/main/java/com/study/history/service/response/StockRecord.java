package com.study.history.service.response;

import com.study.history.service.entity.History;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StockRecord {
    private long userId;
    private int quantity;

    public static StockRecord of(History history) {
        return new StockRecord(
                history.getUserId(),
                history.getQuantity()
        );
    }
}
