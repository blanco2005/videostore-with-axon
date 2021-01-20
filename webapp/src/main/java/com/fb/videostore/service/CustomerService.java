package com.fb.videostore.service;

import com.fb.query.customer.CustomerSummary;

import java.util.List;

public interface CustomerService {

    void create(String customerName);

    List<CustomerSummary> getCustomers();
}
