package com.fb.videostore.adapter;

import com.fb.videostore.CreateRentalCommand;
import com.fb.videostore.service.RentalService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class AxonRentalService implements RentalService {

    @Autowired
    private CommandGateway commandGateway;

    @Override
    public void createRental(String serialNumber, String customer) {
        commandGateway.sendAndWait(new CreateRentalCommand(UUID.randomUUID().toString(), customer, serialNumber));
    }

    @Override
    public void returnMovie(String serialNumber) {

    }
}
