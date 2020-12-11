package com.fb.videostore.service;

import com.fb.query.movie.MovieSummary;

import java.util.List;

public interface MovieService {

    void register(String serialNumber, String title);

    List<MovieSummary> getAllMoviesSummary();

}
