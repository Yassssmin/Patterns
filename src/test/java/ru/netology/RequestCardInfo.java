package ru.netology;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RequestCardInfo {
    private final String city;
    private final String fullName;
    private final String phoneNumber;
}