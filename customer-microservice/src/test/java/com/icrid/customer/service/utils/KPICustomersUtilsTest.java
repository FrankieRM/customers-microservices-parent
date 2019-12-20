package com.icrid.customer.service.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;
class KPICustomersUtilsTest {

    @InjectMocks
    private KPICustomersUtils kpiCustomersUtils;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void average() {
        List<Integer> ages = asList(20, 30);
        double average = kpiCustomersUtils.average(ages);

        assertEquals(25, average);
    }

    @Test
    void deviation() {
        List<Integer> ages = asList(20, 30);
        double average = kpiCustomersUtils.deviation(ages);

        assertEquals(5, average);
    }
}