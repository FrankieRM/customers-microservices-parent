package com.icrid.customer.resource;

import com.icrid.customer.DemoApplication;
import com.icrid.customer.domain.Customer;
import com.icrid.customer.repository.CustomerRepository;
import com.icrid.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.Validator;

import java.util.List;

import static com.icrid.customer.DummyMock.buildCustomer;
import static com.icrid.customer.DummyMock.convertObjectToJsonBytes;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Test de Integracion para el Controlador REST {@link CustomerResource}.
 */
@SpringBootTest(classes = DemoApplication.class)
class CustomerResourceIT {

    @Autowired
    private CustomerResource customerResource;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    private MockMvc restCustomerMockMvc;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private Validator validator;

    private Customer customer;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        this.restCustomerMockMvc = standaloneSetup(customerResource)
                .setMessageConverters(jacksonMessageConverter)
                .setValidator(validator).build();
        customer = buildCustomer();
    }

    @Test
    void createCustomer() throws Exception {
        int databaseSizeBeforeCreate = customerRepository.findAll().size();

        restCustomerMockMvc.perform(post("/creacliente")
                .contentType(APPLICATION_JSON_UTF8)
                .content(convertObjectToJsonBytes(customer)))
                .andExpect(status().isCreated());

        List<Customer> customers = customerRepository.findAll();
        assertThat(customers).hasSize(databaseSizeBeforeCreate + 1);
        Customer newCustomer = customers.get(customers.size() - 1);

        assertThat(newCustomer).isNotNull();
    }

    @Test
    void getKPI() throws Exception {
        customerRepository.saveAndFlush(customer);

        restCustomerMockMvc.perform(get("/kpideclientes")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.average").value(28D))
                .andExpect(jsonPath("$.deviation").value(0D));
    }

    @Test
    void getAllCustomers() throws Exception {
        customerRepository.saveAndFlush(customer);

        restCustomerMockMvc.perform(get("/listclientes")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").value(hasItem(customer.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(customer.getName())))
                .andExpect(jsonPath("$.[*].lastName").value(hasItem(customer.getLastName())))
                .andExpect(jsonPath("$.[*].age").value(hasItem(customer.getAge())))
                .andExpect(jsonPath("$.[*].birthday").exists())
                .andExpect(jsonPath("$.[*].probableDeathOfDate").exists());
    }
}