package com.fb.query.rental.status;

import com.fb.videostore.*;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class RentalStatusView {

  private final RentalStatusRepository rentalStatusRepository;

  public RentalStatusView(
      RentalStatusRepository rentalStatusRepository) {
    this.rentalStatusRepository = rentalStatusRepository;
  }

  @EventHandler
  public void on(RentalCreatedEvent event) {
    rentalStatusRepository.save(new RentalStatus(event.getRentalId(), "APPROVING"));
  }

  @EventHandler
  public void on(RentalApprovedEvent event) {
    rentalStatusRepository.save(new RentalStatus(event.getRentalId(), "ONGOING"));
  }

  @EventHandler
  public void on(RentalRejectedEvent event) {
    rentalStatusRepository.save(new RentalStatus(event.getRentalId(), "REJECTED"));
  }

  @EventHandler
  public void on(RentalTerminatedEvent event) {
    rentalStatusRepository.save(new RentalStatus(event.getRentalId(), "TERMINATED"));
  }

  @QueryHandler
  public RentalStatus handle(RentalStatusQuery query) {
    return rentalStatusRepository
        .findById(query.getRentalId())
        .orElseThrow(() -> new RuntimeException("No rental found"));
  }
}
