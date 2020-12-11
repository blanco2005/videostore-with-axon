package com.fb.videostore.controller;

import com.fb.videostore.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.joining;

@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PutMapping("/customers/{customerName}")
    public ResponseEntity<String> createCustomer(@PathVariable("customerName") String customerName) {
        customerService.create(customerName);
        return ResponseEntity.ok("Customer " + customerName + " created");
    }

    @GetMapping("/customers")
    public ResponseEntity<String> getCustomers() {
        List<Object> customers = customerService.getCustomers();
        String result = customers.stream().map(c -> c.toString()).collect(joining("\n"));
        return ResponseEntity.ok(result);
    }
}
