package com.icrid.customer.utils;

import com.icrid.customer.domain.Customer;
import com.icrid.customer.dto.KPICustomers;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.List;

import static java.time.LocalDate.of;
import static java.time.Month.OCTOBER;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

public final class DummyMock {

    private static final ObjectMapper mapper = createObjectMapper();

    private DummyMock() {
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }

    public static Customer buildCustomer() {
        Customer customer = new Customer();
        customer.setName("Frank");
        customer.setLastName("Rodriguez");
        customer.setAge(28);
        customer.setBirthday(of(1991, OCTOBER, 21));
        return customer;
    }

    public static Customer buildCustomerSaved() {
        Customer customerSaved = buildCustomer();
        customerSaved.setId(1L);
        return customerSaved;
    }

    public static List<Integer> buildAges() {
        List<Customer> customers = singletonList(buildCustomer());
        return customers.stream().map(Customer::getAge).collect(toList());
    }

    public static KPICustomers buildKpiCustomers() {
        return new KPICustomers(10D, 5D);
    }
}
