package com.fb.videostore.adapter;

import com.fb.query.movie.MovieSummary;
import com.fb.videostore.AddMovieToCatalogCommand;
import com.fb.videostore.AllCatalogQuery;
import com.fb.videostore.service.MovieService;
import java.util.concurrent.ExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AxonMovieService implements MovieService {

    @Autowired CommandGateway commandGateway;
    @Autowired QueryGateway queryGateway;

    @Override
    public void register(String serialNumber, String title) {
        AddMovieToCatalogCommand addMovie = new AddMovieToCatalogCommand(serialNumber, title);

        //eventuale volidazione
        commandGateway.sendAndWait(addMovie);
    }

    @Override
    public List<MovieSummary> getAllMovieAvailability() {
        try {
            return queryGateway.query(new AllCatalogQuery(), ResponseTypes.multipleInstancesOf(MovieSummary.class)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Object> getMovieHistory(String serialNumber) {
        return null;
    }
}
