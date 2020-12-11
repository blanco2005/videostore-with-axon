package com.fb.videostore.adapter;

import com.fb.query.rental.RentalSummary;
import com.fb.videostore.CreateRentalCommand;
import com.fb.videostore.OngoingRentalsQuery;
import com.fb.videostore.service.RentalService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class AxonRentalService implements RentalService {

    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private QueryGateway queryGateway;

    @Override
    public void createRental(String serialNumber, String customer) {
        commandGateway.sendAndWait(new CreateRentalCommand(UUID.randomUUID().toString(), customer, serialNumber));
    }

    @Override
    public void returnMovie(String serialNumber) {

    }

    @Override
    public List<RentalSummary> getOngoingRentals() {
        try {
            return queryGateway.query(new OngoingRentalsQuery(), ResponseTypes.multipleInstancesOf(RentalSummary.class)).get();
        } catch (Exception e) {
            throw new RuntimeException("Future problem");
        }
    }
}
