package com.fb.query.movie;

import com.fb.videostore.AllCatalogQuery;
import com.fb.videostore.MovieAddedToCatalogEvent;
import com.fb.videostore.MovieRentedEvent;
import com.fb.videostore.MovieReturnedToCatalogEvent;
import java.util.List;
import java.util.Optional;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class MovieSummaryView {

  private final MovieSummaryRepository movieSummaryRepository;


  public MovieSummaryView(MovieSummaryRepository movieSummaryRepository) {
    this.movieSummaryRepository = movieSummaryRepository;
  }

  @QueryHandler
  public List<MovieSummary> handle(AllCatalogQuery allCatalogQuery) {
    return movieSummaryRepository.findAll();
  }

  @EventSourcingHandler
  public void handle(MovieReturnedToCatalogEvent movieReturnedToCatalogEvent) {
    movieSummaryRepository
        .findById(movieReturnedToCatalogEvent.getTitle()).ifPresent(movieSummary -> {
      movieSummary.setNumberOfCopyAvailable(movieSummary.getNumberOfCopyAvailable() + 1);
      movieSummaryRepository.save(movieSummary);
    });
  }

  @EventSourcingHandler
  public void handle(MovieRentedEvent movieRentedEvent) {
    movieSummaryRepository
        .findById(movieRentedEvent.getTitle()).ifPresent(movieSummary -> {
          movieSummary.setNumberOfCopyAvailable(movieSummary.getNumberOfCopyAvailable() - 1);
          movieSummaryRepository.save(movieSummary);
        });
  }

  @EventSourcingHandler
  public void handle(MovieAddedToCatalogEvent movieAddedToCatalogEvent) {

    movieSummaryRepository
        .findById(movieAddedToCatalogEvent.getTitle()).ifPresentOrElse(movieSummary -> {
          movieSummary.setNumberOfCopyAvailable(movieSummary.getNumberOfCopyAvailable() + 1);
          movieSummary.setNumberOfCopyTotal(movieSummary.getNumberOfCopyTotal() + 1);
          movieSummaryRepository.save(movieSummary);
        },
        () -> {
          movieSummaryRepository.save(new MovieSummary(movieAddedToCatalogEvent.getTitle(),
              1, 1));
        });
  }

}
