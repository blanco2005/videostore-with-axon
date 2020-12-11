package com.fb.query.rental;

import com.fb.videostore.OngoingRentalsQuery;
import com.fb.videostore.RentalApprovedEvent;
import com.fb.videostore.RentalTerminatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class RentalView {

    private final RentalSummaryRepository rentalSummaryRepository;

    public RentalView(RentalSummaryRepository rentalSummaryRepository) {
        this.rentalSummaryRepository = rentalSummaryRepository;
    }

    @EventHandler
    public void on(RentalApprovedEvent event) {
        rentalSummaryRepository.save(new RentalSummary(event.getRentalId(), event.getMovie(), event.getCustomer(), LocalDate.now()));
    }

    @EventHandler
    public void on(RentalTerminatedEvent event) {
        rentalSummaryRepository.deleteById(event.getRentalId());
    }

    @QueryHandler
    public List<RentalSummary> on(OngoingRentalsQuery query) {
        return rentalSummaryRepository.findAll();
    }
}
