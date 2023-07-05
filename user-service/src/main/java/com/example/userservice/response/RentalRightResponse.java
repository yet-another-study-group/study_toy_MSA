package com.example.userservice.response;

import com.example.userservice.constants.RentalRight;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RentalRightResponse {

    @JsonIgnore
    private final RentalRight right;

    private final boolean answer;

    public static RentalRightResponse of(RentalRight right){
        return new RentalRightResponse(right, right.isBool());
    }
}
