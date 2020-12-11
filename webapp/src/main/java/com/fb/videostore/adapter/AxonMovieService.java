package com.fb.videostore.adapter;

import com.fb.query.movie.MovieSummary;
import com.fb.videostore.AllMovieSummaryQuery;
import com.fb.videostore.RegisterMovieCommand;
import com.fb.videostore.service.MovieService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AxonMovieService implements MovieService {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private QueryGateway queryGateway;

    @Override
    public void register(String serialNumber, String title) {
        commandGateway.sendAndWait(new RegisterMovieCommand(serialNumber, title));
    }

    @Override
    public List<MovieSummary> getAllMoviesSummary() {
        try {
            return queryGateway.query(new AllMovieSummaryQuery(), ResponseTypes.multipleInstancesOf(MovieSummary.class)).get();
        } catch (Exception e) {
            throw new RuntimeException("Future problem");
        }
    }


}
