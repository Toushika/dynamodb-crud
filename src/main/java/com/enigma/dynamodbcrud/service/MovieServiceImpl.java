package com.enigma.dynamodbcrud.service;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.enigma.dynamodbcrud.entitties.Movie;
import com.enigma.dynamodbcrud.repositories.MovieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public Movie saveBook(Movie movie) {
        log.info("MovieServiceImpl :: saveBook :: movie :: {}", movie);
        movieRepository.saveMovie(movie);
        return movie;
    }

    @Override
    public Movie getMovieById(String movieId) {
        return movieRepository.getMovieById(movieId);
    }

    @Override
    public PaginatedScanList<Movie> getAllMovie() {
        return movieRepository.getAllMovieByTitle();
    }

    @Override
    public String deleteMovieById(String movieId) {
        return movieRepository.deleteMMovie(movieId);
    }

    @Override
    public Movie updateMovieById(String movieId, Movie movie) {
        return movieRepository.UpdateMovie(movieId, movie);
    }

    @Override
    public String processMovieIdJson(String movieIdJson) {
        ObjectNode node = null;
        String movie_id = null;
        try {
            node = new ObjectMapper().readValue(movieIdJson, ObjectNode.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (node.has("movieId")) {
            movie_id = node.get("movieId").toString().replace('"', ' ');
        }
        return movie_id.trim();
    }

    @Override
    public String saveJsonDataFromFile() {
        return movieRepository.saveMovieJsonFile();
    }
}
