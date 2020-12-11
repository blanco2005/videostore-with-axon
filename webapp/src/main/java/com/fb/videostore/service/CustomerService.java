package com.fb.videostore.service;

import java.util.List;

public interface CustomerService {

    void create(String customerName);

    List<Object> getCustomers();
}
