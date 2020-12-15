package com.fb.rental.saga;

import com.fb.videostore.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.axonframework.modelling.saga.SagaLifecycle.end;

@Saga
public class TerminateRentalSaga {

    private static Logger logger = LoggerFactory.getLogger(TerminateRentalSaga.class);

    @Autowired
    private transient CommandGateway commandGateway;

    private String customer;
    private String rentalId;

    @StartSaga
    @SagaEventHandler(associationProperty = "rentalId")
    public void on(RentalTerminatedEvent event) {
        logger.info("Received RentalTerminatedEvent...starting saga");
        logger.info("I will return the movie");

        this.customer = event.getCustomer();
        this.rentalId = event.getRentalId();

        SagaLifecycle.associateWith("serialNumber", event.getMovie());
        SagaLifecycle.associateWith("customerName", event.getCustomer());
        SagaLifecycle.associateWith("rentalId", event.getRentalId());

        commandGateway.send(new ReturnMovieCommand(event.getMovie()));
    }

    @SagaEventHandler(associationProperty = "serialNumber")
    public void on(MovieReturnedEvent event) {
        logger.info("Received MovieReturnedEvent...");
        logger.info("I will decrease customer rentals");

        commandGateway.send(new DecreaseRentalsCommand(customer));
    }

    @SagaEventHandler(associationProperty = "customerName")
    public void on(CustomerDecreasedRentalsEvent event) {
        logger.info("Received CustomerDecreasedRentalsEvent...");
        logger.info("Rental " + rentalId + "terminated. Ending saga");

        end();
    }
}
