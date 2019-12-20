package com.icrid.customer.resource;

import com.icrid.customer.domain.Customer;
import com.icrid.customer.dto.KPICustomers;
import com.icrid.customer.service.CustomerService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

/**
 * Controlador REST para gestionar {@link com.icrid.customer.resource.CustomerResource}.
 */
@RestController
@Api(tags = "Customer", value = "Customer", description = "API for magaging customers")
@RequestMapping
public class CustomerResource {

    private static final Logger LOGGER = getLogger(CustomerResource.class);

    @Autowired
    private CustomerService customerService;

    /**
     * {@code POST  /creacliente} : Create a new customer.
     *
     * @param customer the customer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customer, or with status {@code 400 (Bad Request)} if the customer has already an ID.
     */
    @PostMapping("/creacliente")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        LOGGER.debug("Solicitud REST para crear un cliente: {}", customer);
        Customer result = customerService.save(customer);
        return status(CREATED).body(result);
    }

    /**
     * {@code GET  /kpideclientes} : get the key performance indicator of customers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kpideclientes")
    public ResponseEntity<KPICustomers> getKPI() {
        LOGGER.debug("Solicitud REST para obtener el kpi de clientes");
        KPICustomers kpiCustomers = customerService.getKPI();
        return ok(kpiCustomers);
    }

    /**
     * {@code GET  /listclientes} : get all customers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customers in body.
     */
    @GetMapping("/listclientes")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        LOGGER.debug("Solicitud REST para obtener el listado total de Clientes");
        List<Customer> customers = customerService.listAll();
        return ok(customers);
    }
}