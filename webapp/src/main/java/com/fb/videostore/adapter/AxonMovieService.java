package com.fb.videostore.adapter;

import com.fb.videostore.service.MovieService;

import java.util.List;

public class AxonMovieService implements MovieService {

    @Override
    public void register(String serialNumber, String title) {

    }

    @Override
    public List<Object> getAllMovieAvailability() {
        return null;
    }

    @Override
    public List<Object> getMovieHistory(String serialNumber) {
        return null;
    }
}
