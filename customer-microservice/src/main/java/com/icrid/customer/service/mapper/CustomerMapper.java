package com.icrid.customer.service.mapper;

import com.icrid.customer.domain.Customer;
import com.icrid.customer.service.utils.CustomerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class CustomerMapper {

    @Autowired
    private CustomerUtils customerUtils;

    public List<Customer> mapProbableDeathOfDate(List<Customer> customers) {
        return customers
                .stream()
                .peek(this::addProbableDeathOfDate)
                .collect(toList());
    }

    private void addProbableDeathOfDate(Customer customer) {
        LocalDate startDate = customerUtils.getInitialDate(customer.getBirthday(), customer.getAge());
        LocalDate endDate = customerUtils.getFinalDate(customer.getBirthday());
        LocalDate probableDeathOfDate = customerUtils.between(startDate, endDate);
        customer.setProbableDeathOfDate(probableDeathOfDate);
    }
}