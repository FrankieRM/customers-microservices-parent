package com.icrid.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.icrid.customer.domain"})
public class CustomerMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerMicroserviceApplication.class, args);
    }
}