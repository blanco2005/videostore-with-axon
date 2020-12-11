package com.fb.videostore.service;

import com.fb.query.movie.MovieSummary;
import com.fb.query.rental.RentalSummary;

import java.util.List;

public interface RentalService {
    void createRental(String serialNumber, String customer);

    void returnMovie(String serialNumber);

    List<RentalSummary> getOngoingRentals();
}
