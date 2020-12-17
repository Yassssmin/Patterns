package ru.netology;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@RequiredArgsConstructor
public class RequestCardInfo {
    private final String city;
    private final LocalDate meetDate;
    private final String fullName;
    private final String phoneNumber;

    public String getFormattedMeetDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return meetDate.format(formatter);
    }
}