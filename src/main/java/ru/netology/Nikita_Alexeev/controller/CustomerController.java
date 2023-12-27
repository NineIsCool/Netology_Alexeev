package ru.netology.Nikita_Alexeev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.Nikita_Alexeev.domain.Customer;
import ru.netology.Nikita_Alexeev.dto.CustomerDTO;
import ru.netology.Nikita_Alexeev.dto.CustomersGetResponse;
import ru.netology.Nikita_Alexeev.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/customers/")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public CustomersGetResponse getCustomers(){
        List<Customer> customers = customerService.getStorage();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName());
            customerDTOS.add(customerDTO);
        }
        return new CustomersGetResponse(customerDTOS);
    }

    @GetMapping("{customersId}")
    public CustomerDTO getCustomer(@PathVariable("customersId") int customerId){
        Customer customer = customerService.getCustomer(customerId);
        return new CustomerDTO(customer.getId(), customer.getName());
    }
    @PostMapping
    public CustomerDTO addCustomer(int id, String name){
        customerService.addCustomer(id, name);
        Customer customer = customerService.getCustomer(id);
        return new CustomerDTO(customer.getId(),customer.getName());
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Object> removeCustomer(@PathVariable int customerId) {
        customerService.removeCustomer(customerId);
        return ResponseEntity.ok().build();
    }
}
