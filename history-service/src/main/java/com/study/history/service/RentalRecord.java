package com.study.history.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RentalRecord {
    private long memberId;
    private long bookId;
    private int quantity;

    public static RentalRecord of(History history) {
        return new RentalRecord(
                history.getMemberId(),
                history.getBookId(),
                history.getQuantity());
    }
}
