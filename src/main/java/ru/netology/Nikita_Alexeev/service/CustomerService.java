package ru.netology.Nikita_Alexeev.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.netology.Nikita_Alexeev.domain.Customer;
import ru.netology.Nikita_Alexeev.dto.CustomerDTO;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class CustomerService {

    private final List<Customer> storage;

    @PostConstruct
    public void initStorage() {
        storage.add(new Customer(0, "Spring"));
        storage.add(new Customer(1, "Boot"));
    }

    public CustomerService(List<Customer> storage) {
        this.storage = storage;
    }

    public void addCustomer(int id, String name) {
        Customer customer = new Customer(id, name);
        storage.add(customer);
    }
    public List<Customer> getCustomers() {
        return new ArrayList<>(storage);
    }
    public Customer getCustomer(int customerId) {
        return getCustomers().stream()
                .filter(c -> c.getId() == customerId)
                .map(c -> new Customer(c.getId(), c.getName()))
                .findFirst().orElse(null);
    }

    public void removeCustomer(int id) {
        storage.removeIf(customer -> customer.getId() == id);
    }
}