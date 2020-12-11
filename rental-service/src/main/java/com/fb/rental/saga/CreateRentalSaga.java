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
public class CreateRentalSaga {

    private static Logger logger = LoggerFactory.getLogger(CreateRentalSaga.class);

    @Autowired
    private transient CommandGateway commandGateway;

    private String customer;
    private String rentalId;

    @StartSaga
    @SagaEventHandler(associationProperty = "rentalId")
    public void on(RentalCreatedEvent event) {
        logger.info("Received RentalCreatedEvent...starting saga");
        logger.info("I will ask to rent the movie");

        this.customer = event.getCustomer();
        this.rentalId = event.getRentalId();

        SagaLifecycle.associateWith("serialNumber", event.getMovie());
        SagaLifecycle.associateWith("customerName", event.getCustomer());

        commandGateway.send(new RentMovieCommand(event.getMovie()));
    }

    @SagaEventHandler(associationProperty = "serialNumber")
    public void on (MovieRentedEvent event) {
        logger.info("Received MovieRenteEvent...");
        logger.info("I will ask request to rent to customer");
        commandGateway.send(new RequestRentalToCustomerCommand(customer));
    }

    @SagaEventHandler(associationProperty = "customerName")
    public void on (CustomerRentalApprovedEvent event) {
        logger.info("Received CustomerRentalApproved...");
        logger.info("I will ask to confirm the rental");
        commandGateway.send(new ApproveRentalCommand(rentalId));
    }

    @SagaEventHandler(associationProperty = "rentalId")
    public void on(RentalApprovedEvent event) {
        logger.info("Received RentalApprovedEvent...terminating the saga");
        end();
    }
}
