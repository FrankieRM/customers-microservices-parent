package com.icrid.customer.dto;

import java.io.Serializable;

/**
 * DTO KPICustomers.
 */
public class KPICustomers implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double average;
    private Double deviation;

    public KPICustomers(Double average, Double deviation) {
        this.average = average;
        this.deviation = deviation;
    }

    public Double getAverage() {
        return average;
    }

    public Double getDeviation() {
        return deviation;
    }
}