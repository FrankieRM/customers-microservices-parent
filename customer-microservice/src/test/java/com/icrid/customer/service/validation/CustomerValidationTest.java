package com.icrid.customer.service.validation;

import com.icrid.customer.domain.Customer;
import com.icrid.customer.errors.NewCustomerWihtIdException;
import com.icrid.customer.errors.WrongAgeOrBirthDayCustomerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static com.icrid.customer.utils.DummyMock.buildCustomer;
import static com.icrid.customer.errors.WrongAgeOrBirthDayCustomerException.WRONG_AGE_OR_BIRTHDAY_CUSTOMER_EXCEPTION_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.MockitoAnnotations.initMocks;

//@SpringBootTest
class CustomerValidationTest {

    @InjectMocks
    private CustomerValidation customerValidation;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void validationOK() {
        Customer customer = buildCustomer();
        customerValidation.validation(customer);
    }

    @Test
    void validationNewCustomerWihtIdException() {
        Customer customer = buildCustomer();
        customer.setId(1L);

        Exception exception = assertThrows(NewCustomerWihtIdException.class,
                () -> customerValidation.validation(customer));

        Assertions.assertEquals(NewCustomerWihtIdException.NEW_CUSTOMER_WIHT_ID_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void validationWrongAgeOrBirthDayCustomerException() {
        Customer customer = buildCustomer();
        customer.setAge(customer.getAge() + 1);

        Exception exception = assertThrows(WrongAgeOrBirthDayCustomerException.class,
                () -> customerValidation.validation(customer));

        assertEquals(WRONG_AGE_OR_BIRTHDAY_CUSTOMER_EXCEPTION_MESSAGE, exception.getMessage());
    }
}