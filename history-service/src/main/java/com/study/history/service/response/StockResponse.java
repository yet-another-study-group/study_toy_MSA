package com.study.history.service.response;

import com.study.history.service.domain.History;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StockResponse {
    private List<StockRecord> bookStockRecords;

    public static StockResponse of(List<History> bookStockRecords) {
        return new StockResponse(bookStockRecords.stream().map(StockRecord::of).collect(Collectors.toList()));
    }
}
