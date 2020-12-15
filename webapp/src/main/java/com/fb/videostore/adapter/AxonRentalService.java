package com.fb.videostore.adapter;

import com.fb.query.rental.status.RentalStatus;
import com.fb.query.rental.summary.RentalSummary;
import com.fb.videostore.*;
import com.fb.videostore.service.RentalService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.axonframework.messaging.responsetypes.ResponseTypes.*;

public class AxonRentalService implements RentalService {

    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private QueryGateway queryGateway;

    @Override
    public String createRental(String serialNumber, String customer) {
        String rentalId = UUID.randomUUID().toString();
        commandGateway.sendAndWait(new CreateRentalCommand(rentalId, customer, serialNumber));
        return rentalId;
    }

    @Override
    public void returnMovie(String serialNumber) {
        try {
            String rentalId = queryGateway.query(new RentalIdByMovieSerialNumber(serialNumber), instanceOf(String.class)).get();
            commandGateway.send(new TerminateRentalCommand(rentalId));
        } catch (Exception e) {
            throw new RuntimeException("Future problem");
        }
    }

    @Override
    public List<RentalSummary> getOngoingRentals() {
        try {
            return queryGateway.query(new OngoingRentalsQuery(), multipleInstancesOf(RentalSummary.class)).get();
        } catch (Exception e) {
            throw new RuntimeException("Future problem");
        }
    }

    @Override
    public RentalStatus getRentalStatus(String rentalId) {
        try {
            return queryGateway.query(new RentalStatusQuery(rentalId), instanceOf(RentalStatus.class)).get();
        } catch (Exception e) {
            throw new RuntimeException("Future problem");
        }
    }
}
