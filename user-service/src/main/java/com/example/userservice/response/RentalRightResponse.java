package com.example.userservice.response;

import com.example.userservice.entity.UserRentalRightStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RentalRightResponse {

    private final String rentalRight;
    private final long userId;

    public static RentalRightResponse answerAndUserID(UserRentalRightStatus rentalRight, long userId){
        return new RentalRightResponse(String.valueOf(rentalRight), userId);
    }
}
