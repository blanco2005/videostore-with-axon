package com.fb.customer.domain;

import com.fb.videostore.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Customer {

    private final static int MAX_NUMBER_OF_RENTALS = 2;

    @AggregateIdentifier
    private String customerName;
    private int numberOfOngoingRentals;

    @CommandHandler
    public Customer(CreateCustomerCommand command) {
        apply(new CustomerCreatedEvent(command.getCustomerName()));
    }

    @EventSourcingHandler
    public void on(CustomerCreatedEvent event) {
        this.customerName = event.getCustomerName();
        this.numberOfOngoingRentals = 0;
    }

    @CommandHandler
    public void handle(RequestRentalCommand command) {
        if (numberOfOngoingRentals == MAX_NUMBER_OF_RENTALS) {
            throw new RuntimeException("Rental rejected for customer " + customerName);
        }
        apply(new CustomerRentalApprovedEvent(customerName));
    }

    @EventSourcingHandler
    public void on(CustomerRentalApprovedEvent event) {
        this.numberOfOngoingRentals = this.numberOfOngoingRentals + 1;
    }

    @CommandHandler
    public void handle(DecreaseRentalsCommand command) {
        apply(new CustomerDecreasedRentalsEvent(customerName));
    }

    @EventSourcingHandler
    public void on(CustomerDecreasedRentalsEvent event) {
        this.numberOfOngoingRentals = this.numberOfOngoingRentals -1;
    }


}
