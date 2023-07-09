package com.example.userservice.service;

import com.example.userservice.constants.RentalRight;
import com.example.userservice.entity.User;
import com.example.userservice.historyApi.HistoryApi;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.response.HistoryApiResponse;
import com.example.userservice.response.HistoryData;
import com.example.userservice.response.RentalRightResponse;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final HistoryApi historyApi;

    public static final int LIMITED_RENTAL_BOOK_COUNT=3;

    public void registration(String email, String password) {
        checkEmailExists(email);
        User newUser = User.of(email, password);
        userRepository.save(newUser);
    }

    private void checkEmailExists(String email){
        if(userRepository.existsUserByEmail(email))
            throw new EntityExistsException();
    }

    public RentalRightResponse checkRentalRight(String email) {
        User user = findUserByUserEmail(email);

        ArrayList<HistoryData> dataList=convertToHistoryData(historyApi.getUserHistory(user.getId()));
        int bookQuantity = countRentalBooks(dataList);

        if (isAvailableRental(bookQuantity)) {
            return RentalRightResponse.of(RentalRight.RENTAL_AVAILABLE);
        } else {
            return RentalRightResponse.of(RentalRight.RENTAL_NOT_AVAILABLE);
        }
    }

    private int countRentalBooks(ArrayList<HistoryData> dataList) {
        int count = dataList.stream()
                .mapToInt(e -> e.getQuantity())
                .sum();

        return count;
    }

    private ArrayList<HistoryData> convertToHistoryData(HistoryApiResponse historyApiResponse){
        Gson gson = new Gson();
        HistoryData[] historyDataArray = gson.fromJson(historyApiResponse.getRentalRecords().toString(), HistoryData[].class);

        ArrayList<HistoryData> historyList = new ArrayList<>();
        Arrays.stream(historyDataArray)
                .forEach(e -> historyList.add(e));

        return historyList;
    }

    private boolean isAvailableRental(int quantity) {
        if (quantity < LIMITED_RENTAL_BOOK_COUNT) {
            return true;
        }
        return false;
    }

    private User findUserByUserEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException());

        return user;
    }

}
