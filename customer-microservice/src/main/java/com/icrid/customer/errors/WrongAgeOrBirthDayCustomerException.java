package com.icrid.customer.errors;

public class WrongAgeOrBirthDayCustomerException extends RuntimeException {

    public static final String WRONG_AGE_OR_BIRTHDAY_CUSTOMER_EXCEPTION_MESSAGE
            = "La fecha de nacimiento y la edad del cliente no están relacionadas";

    public WrongAgeOrBirthDayCustomerException() {
        super(WRONG_AGE_OR_BIRTHDAY_CUSTOMER_EXCEPTION_MESSAGE);
    }
}