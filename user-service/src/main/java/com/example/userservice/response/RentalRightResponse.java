package com.example.userservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RentalRightResponse {

    private final boolean answer;

    public static RentalRightResponse of(boolean answer){
        return new RentalRightResponse(answer);
    }
}
