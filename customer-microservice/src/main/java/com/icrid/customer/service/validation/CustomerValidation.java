package com.icrid.customer.service.validation;

import com.icrid.customer.domain.Customer;
import com.icrid.customer.errors.NewCustomerWihtIdException;
import com.icrid.customer.errors.WrongAgeOrBirthDayCustomerException;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

import static java.time.LocalDate.now;
import static org.slf4j.LoggerFactory.getLogger;

@Component
public class CustomerValidation {

    private static final Logger LOGGER = getLogger(CustomerValidation.class);

    public void validation(Customer customer) {
        valitadionId(customer.getId());
        validationBirthDayAndAge(customer.getBirthday(), customer.getAge());
    }

    private void valitadionId(Long id) {
        if (id != null) {
            LOGGER.debug(NewCustomerWihtIdException.NEW_CUSTOMER_WIHT_ID_EXCEPTION_MESSAGE);
            throw new NewCustomerWihtIdException();
        }
    }

    private void validationBirthDayAndAge(LocalDate birthday, int age) {
        int ageCalculate = Period.between(birthday, now()).getYears();
        if (age != ageCalculate) {
            LOGGER.debug(WrongAgeOrBirthDayCustomerException.WRONG_AGE_OR_BIRTHDAY_CUSTOMER_EXCEPTION_MESSAGE);
            throw new WrongAgeOrBirthDayCustomerException();
        }
    }
}