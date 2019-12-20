package com.icrid.customer.service;

import com.icrid.customer.domain.Customer;
import com.icrid.customer.dto.KPICustomers;
import com.icrid.customer.repository.CustomerRepository;
import com.icrid.customer.service.mapper.CustomerMapper;
import com.icrid.customer.service.mapper.KPICustomersMapper;
import com.icrid.customer.service.validation.CustomerValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static com.icrid.customer.utils.DummyMock.buildAges;
import static com.icrid.customer.utils.DummyMock.buildCustomer;
import static com.icrid.customer.utils.DummyMock.buildCustomerSaved;
import static com.icrid.customer.utils.DummyMock.buildKpiCustomers;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerValidation customerValidation;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private KPICustomersMapper kpiCustomersMapper;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void save() {
        Customer customer = buildCustomer();
        Customer customerSaved = buildCustomerSaved();

        doNothing().when(customerValidation).validation(customer);
        when(customerRepository.save(customer)).thenReturn(customerSaved);

        Customer newCustomer = customerService.save(customer);

        assertNotNull(newCustomer);
        assertEquals(customerSaved, newCustomer);
        assertNotNull(newCustomer.getId());
    }

    @Test
    void getKPI() {
        List<Integer> ages = buildAges();
        when(customerRepository.getCutomersAges()).thenReturn(ages);
        when(kpiCustomersMapper.getKPI(ages)).thenReturn(buildKpiCustomers());

        KPICustomers kpiCustomers = customerService.getKPI();

        assertNotNull(kpiCustomers);
        assertNotNull(kpiCustomers.getAverage());
        assertNotNull(kpiCustomers.getDeviation());
    }

    @Test
    void listAll() {
        List<Customer> customerInRepository = singletonList(buildCustomer());
        when(customerRepository.findAll()).thenReturn(customerInRepository);
        when(customerMapper.mapProbableDeathOfDate(customerInRepository)).thenReturn(customerInRepository);

        List<Customer> customers = customerService.listAll();

        assertNotNull(customers);
        assertEquals(customerInRepository.size(), customers.size());
        assertNotNull(customers.get(0));
    }
}