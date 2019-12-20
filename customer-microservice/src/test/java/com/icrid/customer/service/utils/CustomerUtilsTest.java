package com.icrid.customer.service.utils;

import com.icrid.customer.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.time.LocalDate;

import static com.icrid.customer.DummyMock.buildCustomer;
import static com.icrid.customer.service.utils.CustomerUtils.PROBABLE_INITIAL_DEATH_YEAR;
import static com.icrid.customer.service.utils.CustomerUtils.PROBABLE_MAX_DEATH_YEAR;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

class CustomerUtilsTest {

    @InjectMocks
    private CustomerUtils customerUtils;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    /**
     * 70 - 80
     * fecha de nacimiento  07/08/1959
     * fecha actual		    18/12/2019
     * edad 				60
     * fecha inicial		07/08/1959 + 70 años = 07/08/2029
     * fecha final			31/12/1959 + 80 años = 31/12/2029
     * <p>
     * fecha de nacimiento  07/08/1948
     * fecha actual		    18/12/2019
     * edad 				71
     * fecha inicial		01/01/1948 + 71 años = 01/01/2019
     * fecha final			31/12/1948 + 80 años = 31/12/2028
     */
    @Test
    void getInitialDate() {
        Customer customer = buildCustomer();

        LocalDate initialDate = customerUtils.getInitialDate(customer.getBirthday(), customer.getAge());

        LocalDate expected = customer.getBirthday().plusYears(PROBABLE_INITIAL_DEATH_YEAR).withMonth(JANUARY.getValue()).withDayOfMonth(1);

        assertNotNull(initialDate);
        assertEquals(expected, initialDate);
    }

    @Test
    void getInitialDateWithAgeIsGreaterThanInitialDeathYear() {
        Customer customer = buildCustomer();
        customer.setAge(customer.getAge() + 50);
        LocalDate birthday = customer.getBirthday().minusYears(50);

        LocalDate initialDate = customerUtils.getInitialDate(birthday, customer.getAge());

        LocalDate expected = birthday.plusYears(customer.getAge()).withMonth(JANUARY.getValue()).withDayOfMonth(1);

        assertNotNull(initialDate);
        assertEquals(expected, initialDate);
    }

    @Test
    void getFinalDate() {
        Customer customer = buildCustomer();
        LocalDate birthday = customer.getBirthday();

        LocalDate finalDate = customerUtils.getFinalDate(birthday);

        LocalDate expected = birthday.plusYears(PROBABLE_MAX_DEATH_YEAR).withMonth(DECEMBER.getValue()).withDayOfMonth(31);

        assertNotNull(finalDate);
        assertEquals(expected, finalDate);
    }

    @Test
    void between() {
        Customer customer = buildCustomer();
        LocalDate initialDate = customer.getBirthday();
        LocalDate finalDate = customer.getBirthday().plusYears(1);

        LocalDate randomDate = customerUtils.between(initialDate, finalDate);

        assertNotNull(randomDate);
        assertTrue(randomDate.isAfter(initialDate));
        assertTrue(randomDate.isBefore(finalDate));
    }
}