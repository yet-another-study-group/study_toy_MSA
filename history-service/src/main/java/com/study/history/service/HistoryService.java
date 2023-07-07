package com.study.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;

    public RentalResponse getRentalRecords(long userId) {
        checkUserId(userId);
        return RentalResponse.of(getUserIdRecords(userId));
    }

    private void checkUserId(long userId) {
        getUserIdRecords(userId);

        if (userId == 0) {
            NullPointerException e = new NullPointerException();
            e.printStackTrace();
            throw e;
        }
        else if (getUserIdRecords(userId).isEmpty()) {
            EntityNotFoundException e = new EntityNotFoundException();
            e.printStackTrace();
            throw e;
        }
    }

    private List<History> getUserIdRecords(long userId) {
        return historyRepository.findByUserId(userId);
    }
}
