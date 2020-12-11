package com.fb.query.movie;

import com.fb.videostore.AllMovieSummaryQuery;
import com.fb.videostore.MovieRegisteredEvent;
import com.fb.videostore.MovieRentedEvent;
import com.fb.videostore.MovieReturnedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StoreView {

  private final MovieSummaryRepository movieSummaryRepository;

  public StoreView(MovieSummaryRepository movieSummaryRepository) {
    this.movieSummaryRepository = movieSummaryRepository;
  }

  @EventHandler
  public void on(MovieRegisteredEvent event) {
    MovieSummary movieSummary =
        movieSummaryRepository.findById(event.getTitle()).orElse(new MovieSummary(event.getTitle(), 0, 0));

    movieSummary.setNumberOfCopiesPurchased(movieSummary.getNumberOfCopiesPurchased() + 1);
    movieSummary.setNumberOfCopiesAvailableForRenting(
        movieSummary.getNumberOfCopiesAvailableForRenting() + 1);
    movieSummaryRepository.save(movieSummary);
  }

  @EventHandler
  public void on(MovieRentedEvent event) {
    movieSummaryRepository
        .findById(event.getTitle())
        .ifPresent(
            m -> {
              m.setNumberOfCopiesAvailableForRenting(m.getNumberOfCopiesAvailableForRenting() - 1);
              movieSummaryRepository.save(m);
            });
  }

  @EventHandler
  public void on(MovieReturnedEvent event) {
    movieSummaryRepository.findById(event.getTitle()).ifPresent(m -> {
      m.setNumberOfCopiesAvailableForRenting(m.getNumberOfCopiesAvailableForRenting() +1);
      movieSummaryRepository.save(m);
    });
  }

  @QueryHandler
  public List<MovieSummary> handle(AllMovieSummaryQuery query) {
    return movieSummaryRepository.findAll();
  }
}
