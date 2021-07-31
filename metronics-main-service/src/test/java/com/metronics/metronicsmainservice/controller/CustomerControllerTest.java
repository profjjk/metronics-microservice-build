package com.metronics.metronicsmainservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metronics.metronicsmainservice.Feign.CustomerServiceClient;
import com.metronics.metronicsmainservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class})
@AutoConfigureMockMvc(addFilters = false)
public class CustomerControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DataSource dataSource;

    @MockBean
    private CustomerServiceClient customerServiceClient;

    private ObjectMapper mapper = new ObjectMapper();

    private Customer customerOutput;
    private Customer customerInput;
    private Customer addCustomer;
    private Customer updateCustomer;

    private String inputJson;
    private String outputJson;
    private List<Customer> customerList;
    private String listJson;
    private String updateJson;

    @Before
    public void setUp() throws Exception {
        customerOutput = new Customer();
        customerOutput.setCity("Montclair");
        customerOutput.setState("New Jersey");
        customerOutput.setStreet("Claremont");
        customerOutput.setZipcode("07054");
        customerOutput.setPhone("123-456-7890");
        customerOutput.setContactName("Amanda");
        customerOutput.setBusinessName("Cognizant");
        customerOutput.setId(2);
        outputJson = mapper.writeValueAsString(customerOutput);


        customerInput = new Customer();
        customerInput.setCity("Montclair");
        customerInput.setState("New Jersey");
        customerInput.setStreet("Claremont");
        customerInput.setZipcode("07054");
        customerInput.setPhone("123-456-7890");
        customerInput.setContactName("Amanda");
        customerInput.setBusinessName("Cognizant");
        customerInput.setId(2);

        inputJson = mapper.writeValueAsString(customerInput);


        addCustomer = new Customer();
        addCustomer.setCity("Montclair");
        addCustomer.setState("New Jersey");
        addCustomer.setStreet("Claremont");
        addCustomer.setZipcode("07054");
        addCustomer.setPhone("123-456-7890");
        addCustomer.setContactName("Amanda");
        addCustomer.setBusinessName("Cognizant");
        addCustomer.setId(3);

        updateCustomer = new Customer();
        updateCustomer.setCity("Montclair");
        updateCustomer.setState("New Jersey");
        updateCustomer.setStreet("Claremont");
        updateCustomer.setZipcode("07054");
        updateCustomer.setPhone("123-456-7890");
        updateCustomer.setContactName("Update");
        updateCustomer.setBusinessName("Cognizant");
        updateCustomer.setId(2);

        updateJson = mapper.writeValueAsString(updateCustomer);

        customerList = new ArrayList<>();
        customerList.add(customerOutput);
        customerList.add(addCustomer);
        listJson = mapper.writeValueAsString(customerList);

        doReturn(customerOutput).when(customerServiceClient).getCustomerById(2);
        doReturn(customerOutput).when(customerServiceClient).createCustomer(customerInput);
        doReturn(customerList).when(customerServiceClient).getAllCustomers();



    }

    @Test
    public void shouldReturnCustomerById() throws Exception {

        mockMvc.perform(get("/api/customer/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldReturnAllCustomers() throws Exception {

        mockMvc.perform(get("/api/customer"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(listJson));
    }

    @Test
    public void shouldReturnNewCustomerOnPostRequest() throws Exception {

        mockMvc.perform(post("/api/customer")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldReturnStatusNoContentWhenCustomerUpdated() throws Exception {

        mockMvc.perform(put("/api/customer/2")
                .content(updateJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnStatusNoContentWhenCustomerDeleted() throws Exception {

        mockMvc.perform(delete("/api/customer/2")
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}