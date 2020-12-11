package com.fb.videostore.controller;

import com.fb.videostore.service.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.format;

@RestController
public class RentalController {

    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/movies/{serialNumber}/rent")
    public ResponseEntity<String> rent(@PathVariable("serialNumber") String serialNumber,
                                       @RequestParam("customer") String customer) {

        rentalService.createRental(serialNumber, customer);
        return ResponseEntity.ok(format("Movie with sn %s rented to %s", serialNumber, customer));
    }

    @PostMapping("/movies/{serialNumber}/return")
    public ResponseEntity<String> returnMovie(@PathVariable("serialNumber") String serialNumber,
                                       @RequestParam("customer") String customer) {

        rentalService.returnMovie(serialNumber);
        return ResponseEntity.ok(format("Movie with sn %s returned", serialNumber));
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(RuntimeException e) {
        return new ResponseEntity<>("Some problem: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
