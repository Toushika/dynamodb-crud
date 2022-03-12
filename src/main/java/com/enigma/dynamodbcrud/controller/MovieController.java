package com.enigma.dynamodbcrud.controller;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.enigma.dynamodbcrud.entitties.Movie;
import com.enigma.dynamodbcrud.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/save/movie")
    public Movie saveMovie(@RequestBody Movie movie) {
        log.info("MovieController :: saveMovie :: movie :: {}", movie);
        return movieService.saveBook(movie);
    }

    @PostMapping("/get-movie-id")
    public Movie getMovieById(@RequestBody String movieIdJson) {
        log.info("MovieController :: getMovieById :: movieId :: {}", movieIdJson);
        String movieId = movieService.processMovieIdJson(movieIdJson);
        return movieService.getMovieById(movieId);
    }

    @PostMapping("/get-all-movie")
    public PaginatedScanList<Movie> getAllMovie() {
        log.info("MovieController :: getAllMovie :: {}", movieService.getAllMovie());
        return movieService.getAllMovie();
    }

    @PostMapping("/delete-movie")
    public String deleteMovieById(@RequestBody String movieIdJson) {
        String movieId = movieService.processMovieIdJson(movieIdJson);
        log.info("MovieController :: deleteMovieById :: movieId :: {}", movieId);
        return movieService.deleteMovieById(movieId);
    }

    @PostMapping("/update-movie/{movieId}")
    public Movie updateMovieById(@PathVariable("movieId") String movieId, @RequestBody Movie movie) {
        log.info("MovieController :: updateMovieById :: movieId :: {} :: movie :: {}", movieId, movie);
        return movieService.updateMovieById(movieId, movie);
    }

    @PostMapping("/save-json-data-from-file")
    public String saveJsonDataFromFile() {
        movieService.saveJsonDataFromFile();
        return "Data from Json file has been saved successfully";

    }
}
