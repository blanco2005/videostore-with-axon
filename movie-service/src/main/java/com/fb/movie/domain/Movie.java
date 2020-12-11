package com.fb.movie.domain;

import com.fb.videostore.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Movie {

    @AggregateIdentifier
    private String serialNumber;

    private String title;

    private boolean isAvailable;

    @CommandHandler
    public Movie(RegisterMovieCommand command) {
        apply(new MovieRegisteredEvent(command.getSerialNumber(), command.getTitle()));
    }

    @EventSourcingHandler
    public void on(MovieRegisteredEvent event) {
        this.serialNumber = event.getSerialNumber();
        this.title = event.getTitle();
        this.isAvailable = true;
    }

    @CommandHandler
    public void handle(RentMovieCommand command) {
        if (!isAvailable) {
            throw new RuntimeException("Movie already rented!");
        }
        apply(new MovieRentedEvent(command.getSerialNumber()));
    }

    @EventSourcingHandler
    public void on(MovieRentedEvent event) {
        isAvailable = false;
    }

    @CommandHandler
    public void handle(ReturnMovieCommand command) {
        apply(new MovieReturnedEvent(command.getSerialNumber()));
    }

    @EventSourcingHandler
    public void on(MovieReturnedEvent event) {
        isAvailable = true;
    }




}
