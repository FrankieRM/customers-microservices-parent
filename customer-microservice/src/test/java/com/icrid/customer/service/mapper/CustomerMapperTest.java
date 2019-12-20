package com.icrid.customer.service.mapper;

import com.icrid.customer.domain.Customer;
import com.icrid.customer.service.utils.CustomerUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static com.icrid.customer.utils.DummyMock.buildCustomer;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
class CustomerMapperTest {

    @InjectMocks
    private CustomerMapper customerMapper;

    @Mock
    private CustomerUtils customerUtils;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void mapProbableDeathOfDate() {
        Customer customer = buildCustomer();

        when(customerUtils.getInitialDate(customer.getBirthday(), customer.getAge()))
                .thenReturn(customer.getBirthday());
        when(customerUtils.getFinalDate(customer.getBirthday()))
                .thenReturn(customer.getBirthday().plusYears(1));
        when(customerUtils.between(customer.getBirthday(), customer.getBirthday().plusYears(1)))
                .thenReturn(customer.getBirthday().plusMonths(1));

        List<Customer> customers = singletonList(customer);
        List<Customer> customersModifieds = customerMapper.mapProbableDeathOfDate(customers);

        assertNotNull(customersModifieds);
        assertEquals(customers.size(), customersModifieds.size());
        assertNotNull(customersModifieds.get(0).getProbableDeathOfDate());
    }
}