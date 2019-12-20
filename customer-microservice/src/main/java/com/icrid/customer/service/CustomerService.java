package com.icrid.customer.service;

import com.icrid.customer.domain.Customer;
import com.icrid.customer.dto.KPICustomers;
import com.icrid.customer.repository.CustomerRepository;
import com.icrid.customer.service.mapper.CustomerMapper;
import com.icrid.customer.service.mapper.KPICustomersMapper;
import com.icrid.customer.service.validation.CustomerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerValidation customerValidation;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private KPICustomersMapper kpiCustomersMapper;

    public Customer save(Customer customer) {
        customerValidation.validation(customer);
        return customerRepository.save(customer);
    }

    public KPICustomers getKPI() {
        List<Integer> cutomersAges = customerRepository.getCutomersAges();
        return kpiCustomersMapper.getKPI(cutomersAges);
    }

    public List<Customer> listAll() {
        List<Customer> allCustomers = customerRepository.findAll();
        return customerMapper.mapProbableDeathOfDate(allCustomers);
    }
}