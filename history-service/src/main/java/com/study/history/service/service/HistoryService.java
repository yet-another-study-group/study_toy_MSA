package com.study.history.service.service;

import com.study.history.service.response.exception.ExceptionResponse;
import com.study.history.service.domain.History;
import com.study.history.service.response.RentalResponse;
import com.study.history.service.repository.HistoryRepository;
import com.study.history.service.response.exception.UserIdException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;

    public RentalResponse getRentalRecords(long userId) {
        checkUserId(userId);
        return RentalResponse.of(getUserIdRecords(userId));
    }

    private void checkUserId(long userId) {
        getUserIdRecords(userId);

        if (getUserIdRecords(userId).isEmpty()) {
            EntityNotFoundException userIdNotFoundException = new EntityNotFoundException("The User ID " + userId + " could not be found.");
            log.error("userId 처리 중 error 발생한 userId: " + userId, userIdNotFoundException);
            throw new UserIdException("해당 User Id를 찾을 수 없습니다.");
        }
    }

    private List<History> getUserIdRecords(long userId) {
        return historyRepository.findByUserId(userId);
    }
}
