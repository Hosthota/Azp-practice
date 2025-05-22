package com.api.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateRandomValue {

    public String generateEntryName(){
        LocalDateTime dateTime = LocalDateTime.now();
        Random random = new Random();

        int day = dateTime.getDayOfMonth();
        int month = dateTime.getMonthValue();
        int sec = dateTime.getSecond();
        int num = random.nextInt(100);
        return "Allianz_R_" + month + day + sec + num;
    }

    public String generateEntryNameForPatch(){
        LocalDateTime dateTime = LocalDateTime.now();
        int day = dateTime.getDayOfMonth();
        int month = dateTime.getMonthValue();
        int sec = dateTime.getSecond();

        return "New_Allianz_" + month + day + sec;
    }

    public String generateEndTimeForPatch() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDateTime.now().plusDays(4).format(format);
    }
}
