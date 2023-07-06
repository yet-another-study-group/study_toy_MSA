package com.study.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;


@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;

    public RentalResponse getRentalRecords(long memberId) {
        checkMemberId(memberId);
        List<History> rentalRecords = getHistories(memberId);
        List<RentalResponse.RentalRecord> rentalRecordList = rentalRecords.stream()
                .map(record -> new RentalResponse.RentalRecord(record.getMemberId(), record.getBookId(), record.getQuantity()))
                .collect(Collectors.toList());
        return RentalResponse.of(rentalRecordList);
    }


    private void checkMemberId(long memberId) {
        List<History> rentalRecords = getHistories(memberId);

        if (memberId == 0) {
            NullPointerException e = new NullPointerException();
            e.printStackTrace();
            throw e;
        }
        else if (rentalRecords.isEmpty()) {
            EntityNotFoundException e = new EntityNotFoundException();
            e.printStackTrace();
            throw e;
        }
    }

    private List<History> getHistories(long memberId) {
        return historyRepository.findByMemberId(memberId);
    }
}
