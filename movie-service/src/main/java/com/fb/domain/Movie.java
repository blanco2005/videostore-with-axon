package com.fb.domain;

import com.fb.videostore.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class Movie {

  @AggregateIdentifier
  private String serialNumber;
  private String title;
  private Boolean isAvailable;

  public Movie() {

  }

  @CommandHandler
  public Movie(AddMovieToCatalogCommand addMovieToCatalogCommand) {
    AggregateLifecycle
        .apply(new MovieAddedToCatalogEvent(addMovieToCatalogCommand.getSerialNumber(),
            addMovieToCatalogCommand.getTitle()));
  }

  @CommandHandler
  public void handle(RentCommand rentCommand) {
    if (!isAvailable) {
      throw new RuntimeException();
    }
    AggregateLifecycle.apply(new MovieRentedEvent(rentCommand.getSerialNumber(), this.title));
  }

  @CommandHandler
  public void handle(ReturnMovieCommand returnMovieCommand) {
    if (isAvailable) {
      throw new RuntimeException(returnMovieCommand.getSerialNumber());
    }

    AggregateLifecycle.apply(new MovieReturnedToCatalogEvent(returnMovieCommand.getSerialNumber(), this.title));
  }

  @EventSourcingHandler
  public void handle(MovieReturnedToCatalogEvent movieReturnedToCatalogEvent) {
    isAvailable = true;
  }

  @EventSourcingHandler
  public void handle(MovieRentedEvent movieRentedEvent) {
    this.isAvailable = false;
  }

  @EventSourcingHandler
  public void handle(MovieAddedToCatalogEvent movieAddedToCatalogEvent) {
    this.serialNumber = movieAddedToCatalogEvent.getSerialNumber();
    this.title = movieAddedToCatalogEvent.getTitle();
    this.isAvailable = true;
  }

}
