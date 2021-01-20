package com.fb.videostore.controller;

import com.fb.query.customer.CustomerSummary;
import com.fb.videostore.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<CustomerSummary> customers = customerService.getCustomers();
        String result = customers.stream().map(c -> c.toString()).collect(joining("\n"));
        return ResponseEntity.ok(result);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(RuntimeException e) {
        return new ResponseEntity<>("Some problem: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
