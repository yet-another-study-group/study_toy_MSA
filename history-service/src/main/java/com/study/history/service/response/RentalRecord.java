package com.study.history.service.response;

import com.study.history.service.domain.History;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RentalRecord {
    private long bookId;
    private int quantity;

    public static RentalRecord of(History history) {
        return new RentalRecord(
                history.getBookId(),
                history.getQuantity());
    }
}
