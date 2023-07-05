package com.example.userservice.constants;

import lombok.Getter;

public enum RentalRight {

    RENTAL_AVAILABLE("대여가능", true),
    RENTAL_NOT_AVAILABLE("대여불가능", false);

    @Getter
    private String str;
    @Getter
    private boolean bool;

    RentalRight(String str, boolean bool) {
        this.str = str;
        this.bool = bool;
    }
}
