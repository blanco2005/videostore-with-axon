package com.fb.videostore.service;

import com.fb.query.rental.status.RentalStatus;
import com.fb.query.rental.summary.RentalSummary;

import java.util.List;

public interface RentalService {
    String createRental(String serialNumber, String customer);

    void returnMovie(String serialNumber);

    List<RentalSummary> getOngoingRentals();

    RentalStatus getRentalStatus(String rentalId);
}
