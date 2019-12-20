package com.icrid.customer.service.mapper;

import com.icrid.customer.dto.KPICustomers;
import com.icrid.customer.service.utils.KPICustomersUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static com.icrid.customer.DummyMock.buildAges;
import static com.icrid.customer.DummyMock.buildKpiCustomers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class KPICustomersMapperTest {

    @InjectMocks
    private KPICustomersMapper kpiCustomersMapper;

    @Mock
    private KPICustomersUtils kpiCustomersUtils;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void getKPI() {
        List<Integer> ages = buildAges();
        KPICustomers kpiCustomersMock = buildKpiCustomers();

        when(kpiCustomersUtils.average(ages)).thenReturn(kpiCustomersMock.getAverage());
        when(kpiCustomersUtils.deviation(ages)).thenReturn(kpiCustomersMock.getDeviation());

        KPICustomers kpiCustomers = kpiCustomersMapper.getKPI(ages);

        assertNotNull(kpiCustomers);
        assertNotNull(kpiCustomers.getAverage());
        assertNotNull(kpiCustomers.getDeviation());
        assertEquals(kpiCustomersMock.getAverage(), kpiCustomers.getAverage());
        assertEquals(kpiCustomersMock.getDeviation(), kpiCustomers.getDeviation());
    }
}