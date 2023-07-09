package com.example.userservice.response;

import lombok.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryApiResponse {

    private ArrayList<HistoryData> rentalRecords;
}
