package com.study.history.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RentalResponse {
    private List<RentalRecord> rentalRecords;

    public static RentalResponse of(List<RentalRecord> rentalRecords) {
        return new RentalResponse(rentalRecords);
    }

    @Getter
    @AllArgsConstructor
    public static class RentalRecord {
        private long memberId;
        private long bookId;
        private int quantity;
    }
}
