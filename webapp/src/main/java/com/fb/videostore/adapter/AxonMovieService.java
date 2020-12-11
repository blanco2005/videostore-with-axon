package com.fb.videostore.adapter;

import com.fb.videostore.RegisterMovieCommand;
import com.fb.videostore.service.MovieService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AxonMovieService implements MovieService {

    @Autowired
    private CommandGateway commandGateway;

    @Override
    public void register(String serialNumber, String title) {
        commandGateway.send(new RegisterMovieCommand(serialNumber, title));
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
