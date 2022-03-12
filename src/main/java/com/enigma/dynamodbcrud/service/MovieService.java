package com.enigma.dynamodbcrud.service;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.enigma.dynamodbcrud.entitties.Movie;

public interface MovieService {
    Movie saveBook(Movie movie);

    Movie getMovieById(String movieId);

    PaginatedScanList<Movie> getAllMovie();

    String deleteMovieById(String movieId);

    Movie updateMovieById(String movieId, Movie movie);

    String processMovieIdJson(String movieIdJson);

    String saveJsonDataFromFile();
}
