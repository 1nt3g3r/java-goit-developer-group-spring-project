package com.goit.devgroup.spring.time;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TimeService {
    public String getCurrentTimeByUtc() {
        return getCurrentTimeBySpecificTimezone("UTC");
    }

    public String getCurrentTimeBySpecificTimezone(String tz) {
        Instant now = Instant.now();
        return ZonedDateTime.ofInstant(now, ZoneId.of(tz)).format(DateTimeFormatter.ofPattern(
                "HH:mm:ss"
        ));
    }

    public String getCurrentTimeByServerTimezone() {
        return LocalDateTime.now() + "";
    }

    public List<Integer> generateRandomNumbers() {
        Random random = new Random();
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            randomNumbers.add(random.nextInt(1000));
        }
        return randomNumbers;
    }

    public static void main(String[] args) {
        System.out.println("ZoneId.getAvailableZoneIds() = " + ZoneId.getAvailableZoneIds());
    }

}
