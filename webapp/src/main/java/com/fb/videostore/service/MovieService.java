package com.fb.videostore.service;

import com.fb.domain.Movie;
import com.fb.query.movie.MovieSummary;
import com.fb.videostore.AllCatalogQuery;
import java.util.List;

public interface MovieService {

    void register(String serialNumber, String title);

    List<MovieSummary> getAllMovieAvailability();

    List<Object> getMovieHistory(String serialNumber);
}
