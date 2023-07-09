package com.example.userservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class HistoryData {

    private Long bookId;
    private Integer quantity;
}
