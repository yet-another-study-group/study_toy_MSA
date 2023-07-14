package com.example.userservice.response;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class HistoryApiResponse {

    private ArrayList<HistoryData> rentalRecords;
}
