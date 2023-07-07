package com.study.history.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RentalResponse {
    private List<RentalRecord> UserRentalRecords;

        public static RentalResponse of(List<History> rentalRecords) {
            List<RentalRecord> rentalRecordList = rentalRecords.stream()
                    .map(RentalRecord::of)
                    .collect(Collectors.toList());
            return new RentalResponse(rentalRecordList);
        }
 }
