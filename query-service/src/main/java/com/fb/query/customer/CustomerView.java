package com.fb.query.customer;

import com.fb.videostore.AllCustomersSummaryQuery;
import com.fb.videostore.CustomerCreatedEvent;
import com.fb.videostore.CustomerDecreasedRentalsEvent;
import com.fb.videostore.CustomerRentalApprovedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class CustomerView {

    private final CustomerSummaryRepository customerSummaryRepository;

    public CustomerView(CustomerSummaryRepository customerSummaryRepository) {
        this.customerSummaryRepository = customerSummaryRepository;
    }

    @EventHandler
    public void on(CustomerCreatedEvent event) {
        customerSummaryRepository.save(
                new CustomerSummary(event.getCustomerName(), 0, LocalDate.now()));
    }

    @EventHandler
    public void on(CustomerRentalApprovedEvent event) {
        customerSummaryRepository
                .findById(event.getCustomerName())
                .ifPresent(c -> {
                    c.setNumberOfOngoingRentals(c.getNumberOfOngoingRentals() + 1);
                    customerSummaryRepository.save(c);
                });
    }

    @EventHandler
    public void on(CustomerDecreasedRentalsEvent event) {
        customerSummaryRepository
                .findById(event.getCustomerName())
                .ifPresent(c -> {
                    c.setNumberOfOngoingRentals(c.getNumberOfOngoingRentals() - 1);
                    customerSummaryRepository.save(c);
                });
    }

    @QueryHandler
    public List<CustomerSummary> handle(AllCustomersSummaryQuery query) {
        return customerSummaryRepository.findAll();
    }
}