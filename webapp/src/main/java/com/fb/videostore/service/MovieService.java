package com.fb.videostore.service;

import java.util.List;

public interface MovieService {

    void register(String serialNumber, String title);

    List<Object> getAllMovieAvailability();

    List<Object> getMovieHistory(String serialNumber);
}
