package com.icrid.customer.errors;

public class NewCustomerWihtIdException extends RuntimeException {

    public static final String NEW_CUSTOMER_WIHT_ID_EXCEPTION_MESSAGE = "Un nuevo cliente no debe tener un ID asignado";

    public NewCustomerWihtIdException() {
        super(NEW_CUSTOMER_WIHT_ID_EXCEPTION_MESSAGE);
    }
}