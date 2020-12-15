package com.fb.rental.domain;

import com.fb.videostore.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static com.fb.rental.domain.Rental.RentalStatus.*;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Rental {

    @AggregateIdentifier
    private String rentalId;
    private String customer;
    private String movie;
    private RentalStatus status;

    public Rental() {
    }

    @CommandHandler
    public Rental(CreateRentalCommand command) {
        apply(new RentalCreatedEvent(command.getRentalId(), command.getCustomer(), command.getMovie()));
    }

    @EventSourcingHandler
    public void on(RentalCreatedEvent event) {
        this.rentalId = event.getRentalId();
        this.customer = event.getCustomer();
        this.movie = event.getMovie();
        this.status = APPROVING;
    }

    @CommandHandler
    public void handle(ApproveRentalCommand command) {
        apply(new RentalApprovedEvent(command.getRentalId(), movie, customer));
    }

    @EventSourcingHandler
    public void on(RentalApprovedEvent event) {
        this.status = ONGOING;
    }

    @CommandHandler
    public void handle(TerminateRentalCommand command) {
        apply(new RentalTerminatedEvent(command.getRentalId(), customer, movie));
    }

    @EventSourcingHandler
    public void on(RentalTerminatedEvent event) {
        this.status = TERMINATED;
    }

    @CommandHandler
    public void handle(RejectRentalCommand command) {
        apply(new RentalRejectedEvent(command.getRentalId()));
    }

    @EventSourcingHandler
    public void on(RentalRejectedEvent event) {
        this.status = REJECTED;
    }

    enum RentalStatus {
        APPROVING,
        ONGOING,
        REJECTED,
        TERMINATED
    }
}
