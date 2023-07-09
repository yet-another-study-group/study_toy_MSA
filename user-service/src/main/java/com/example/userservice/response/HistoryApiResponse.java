package com.example.userservice.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@Getter
public class HistoryApiResponse {

    private ArrayList<Map<String, Object>> rentalRecords;
}
