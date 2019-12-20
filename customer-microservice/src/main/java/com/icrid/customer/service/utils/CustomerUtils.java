package com.icrid.customer.service.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.time.LocalDate.ofEpochDay;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.util.concurrent.ThreadLocalRandom.current;

@Component
public class CustomerUtils {

    public static final int PROBABLE_INITIAL_DEATH_YEAR = 60;
    public static final int PROBABLE_MAX_DEATH_YEAR = 90;

    public LocalDate getInitialDate(LocalDate birthday, int age) {
        int initialDeathOfDate = PROBABLE_INITIAL_DEATH_YEAR;

        if (age > initialDeathOfDate) {
            initialDeathOfDate = age;
        }

        return birthday.plusYears(initialDeathOfDate)
                .withMonth(JANUARY.getValue())
                .withDayOfMonth(1);
    }

    public LocalDate getFinalDate(LocalDate birthday) {
        return birthday.plusYears(PROBABLE_MAX_DEATH_YEAR)
                .withMonth(DECEMBER.getValue())
                .withDayOfMonth(31);
    }

    public LocalDate between(LocalDate initialInclusive, LocalDate finalExclusive) {
        long startEpochDay = initialInclusive.toEpochDay();
        long endEpochDay = finalExclusive.toEpochDay();
        long randomDay = current().nextLong(startEpochDay, endEpochDay);
        return ofEpochDay(randomDay);
    }
}