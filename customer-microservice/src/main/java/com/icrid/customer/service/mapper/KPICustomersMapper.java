package com.icrid.customer.service.mapper;

import com.icrid.customer.dto.KPICustomers;
import com.icrid.customer.service.utils.KPICustomersUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KPICustomersMapper {

    @Autowired
    private KPICustomersUtils kpiCustomersUtils;

    public KPICustomers getKPI(List<Integer> ages) {
        return new KPICustomers(
                kpiCustomersUtils.average(ages),
                kpiCustomersUtils.deviation(ages));
    }
}