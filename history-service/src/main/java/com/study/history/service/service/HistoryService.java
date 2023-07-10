package com.study.history.service.service;

import com.study.history.service.response.RentalResponse;
import com.study.history.service.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;


@Service
@Slf4j
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;

    public RentalResponse getRentalRecords(long userId) {
        if (historyRepository.findAllByUserId(userId).isEmpty()) {
            EntityNotFoundException userEntityNotFoundException = new EntityNotFoundException("The User ID " + userId + " could not be found.");
            log.error("userId 처리 중 error 발생한 userId: " + userId, userEntityNotFoundException);
            throw new EntityNotFoundException("해당 User Id를 찾을 수 없습니다.");
        }
        return RentalResponse.of(historyRepository.findAllByUserId(userId));
    }
}


