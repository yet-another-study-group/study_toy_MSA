package com.example.userservice;

import com.example.userservice.entity.User;
import com.example.userservice.historyApi.HistoryApi;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.response.HistoryApiResponse;
import com.example.userservice.response.HistoryData;
import com.example.userservice.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    HistoryApi historyApi;

    @InjectMocks
    UserService userService;

    String email;
    HistoryApiResponse historyApiResponse;

    @BeforeEach
    void setting(){

        User user = mock(User.class);
        historyApiResponse = new HistoryApiResponse();

        email = "h1@naver.com";
        given(user.getId()).willReturn(1L);
        given(userRepository.findByEmail(Mockito.anyString())).willReturn(Optional.of(user));
        given(historyApi.getUserHistory(user.getId())).willReturn(historyApiResponse);

    }

    @Test
    @DisplayName("answer(false), 책이 4권이어서 대여가 불가능")
    void test1() {

        //given
        ArrayList<HistoryData> dataList = new ArrayList<>() {{
            add(new HistoryData(2L, 2));
            add(new HistoryData(3L, 1));
            add(new HistoryData(1L, 1));
        }};
        historyApiResponse.setRentalRecords(dataList);

        //when, then
        String expected = "false";
        String answer = String.valueOf(userService.checkRentalRight(email).isAnswer());
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("answer(true), 책이 2권이여서 대여가 가능")
    void test2() {

        //given
        ArrayList<HistoryData> dataList = new ArrayList<>() {{
            add(new HistoryData(3L, 1));
            add(new HistoryData(1L, 1));
        }};
        historyApiResponse.setRentalRecords(dataList);

        //when, then
        String expected = "true";
        String answer = String.valueOf(userService.checkRentalRight(email).isAnswer());
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("answer(true), 아예 처음 빌려서 기록이 없는 사람인 경우")
    void test3() {

        //given
        ArrayList<HistoryData> dataList = new ArrayList<>();
        historyApiResponse.setRentalRecords(dataList);

        //when, then
        String expected = "true";
        String answer = String.valueOf(userService.checkRentalRight(email).isAnswer());
        assertEquals(expected, answer);
    }

}
