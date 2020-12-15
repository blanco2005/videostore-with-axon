package com.fb.videostore.controller;

import com.fb.query.rental.summary.RentalSummary;
import com.fb.videostore.service.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static org.springframework.http.ResponseEntity.*;

@RestController
public class RentalController {

    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/movies/{serialNumber}/rent")
    public ResponseEntity<String> rent(@PathVariable("serialNumber") String serialNumber,
                                       @RequestParam("customer") String customer) {

        String rentalId = rentalService.createRental(serialNumber, customer);
        return ok(format("Requested creation of rental %s ", rentalId));
    }

    @PostMapping("/movies/{serialNumber}/return")
    public ResponseEntity<String> returnMovie(@PathVariable("serialNumber") String serialNumber) {

        rentalService.returnMovie(serialNumber);
        return ok(format("Movie with sn %s returned", serialNumber));
    }

    @GetMapping("/rentals")
    public ResponseEntity<String> currentRentals() {
        List<RentalSummary> ongoingRentals = rentalService.getOngoingRentals();
        String result = ongoingRentals.stream().map(m -> m.toString()).collect(joining("\n"));
        return ok(result);
    }

    @GetMapping("/rentals/{rentalId}/status")
    public ResponseEntity<String> rentalStatus(@PathVariable("rentalId") String rentalId) {
        return ok(rentalService.getRentalStatus(rentalId).getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(RuntimeException e) {
        return new ResponseEntity<>("Some problem: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
