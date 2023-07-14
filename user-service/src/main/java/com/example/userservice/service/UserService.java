package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.historyApi.HistoryApi;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.response.HistoryApiResponse;
import com.example.userservice.response.HistoryData;
import com.example.userservice.response.RentalRightResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final HistoryApi historyApi;

    public static final int LIMITED_RENTAL_BOOK_COUNT=3;

    public void registration(String email, String password) {
        if(userRepository.existsUserByEmail(email))
            throw new EntityExistsException();
        User newUser = User.of(email, password);
        userRepository.save(newUser);
    }

    public RentalRightResponse checkRentalRight(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException());
        HistoryApiResponse response=historyApi.getUserHistory(user.getId());
        int bookQuantity = countRentalBooks(response.getRentalRecords());

        if (bookQuantity < LIMITED_RENTAL_BOOK_COUNT) {
            log.debug("대여 가능");
            return RentalRightResponse.of(true);
        } else {
            log.debug("대여 불가능");
            return RentalRightResponse.of(false);
        }
    }

    private int countRentalBooks(ArrayList<HistoryData> dataList) {
        int count = dataList.stream()
                .mapToInt(e -> e.getQuantity())
                .sum();

        return count;
    }

}
