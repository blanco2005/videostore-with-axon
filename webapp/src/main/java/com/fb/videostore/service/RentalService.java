package com.fb.videostore.service;

public interface RentalService {
    void createRental(String serialNumber, String customer);

    void returnMovie(String serialNumber);
}
