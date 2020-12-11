package com.fb.videostore.controller;

import com.fb.videostore.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@RestController
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PutMapping("/movies/{serialNumber}")
    public ResponseEntity<String> register(@PathVariable("serialNumber") String serialNumber,
                                           @RequestParam("title") String title) {

        movieService.register(serialNumber, title);
        return ResponseEntity.ok(format("Movie with sn %s and title %s registered", serialNumber, title));
    }

    @GetMapping("/movies")
    public ResponseEntity<String> moviesAvailability() {
        List<Object> allMovieAvailability = movieService.getAllMovieAvailability();
        String result = allMovieAvailability.stream().map(m -> m.toString()).collect(Collectors.joining("\n"));
        return ResponseEntity.ok(result);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(RuntimeException e) {
        return new ResponseEntity<String>("Some problem: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
