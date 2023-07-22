package com.study.Book.Service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BookAvailabilityResponse {
    private boolean available;
    private String message;
}
