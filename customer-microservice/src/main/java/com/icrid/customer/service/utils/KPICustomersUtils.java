package com.icrid.customer.service.utils;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
@Component
public class KPICustomersUtils {

    public double average(List<Integer> ages) {
        return ages.stream()
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble();
    }

    public double deviation(List<Integer> ages) {
        int size = ages.size();
        double average = average(ages);
        double sum = ages.stream().mapToDouble(age -> pow(age - average, 2)).sum();

        return sqrt(sum / (double) size);
    }
}