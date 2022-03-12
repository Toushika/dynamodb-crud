package com.enigma.dynamodbcrud.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.enigma.dynamodbcrud.entitties.Movie;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieRepository {
    private final DynamoDBMapper dynamoDBMapper;
    private final Table table;

    public Movie saveMovie(Movie movie) {
        dynamoDBMapper.save(movie);
        return movie;
    }

    public Movie getMovieById(String movieId) {
        System.out.println(dynamoDBMapper.load(Movie.class, movieId));
        return dynamoDBMapper.load(Movie.class, movieId);
    }

    public PaginatedScanList<Movie> getAllMovieByTitle() {
        return dynamoDBMapper.scan(Movie.class, new DynamoDBScanExpression());
    }

    public Movie UpdateMovie(String movieId, Movie movieForUpdate) {
        Movie movie = getMovieById(movieId);
        if (movie.getMovieId().equals(movieForUpdate.getMovieId()))
            dynamoDBMapper.save(movieForUpdate);
        return movieForUpdate;
    }

    public String deleteMMovie(String movieId) {
        dynamoDBMapper.delete(dynamoDBMapper.load(Movie.class, movieId));
        return movieId + "- has been deleted";
    }

    // Not working properly
    public String saveMovieJsonFile() {
        JsonParser parser = null;
        try {
            parser = new JsonFactory().createParser(new File("/home/toushika/Desktop/java-learn/movies.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonNode rootNode = null;
        try {
            rootNode = new ObjectMapper().readTree(parser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<JsonNode> iter = rootNode.iterator();

        ObjectNode currentNode;

        while (iter.hasNext()) {
            currentNode = (ObjectNode) iter.next();
            System.out.println("currentNode = " + currentNode);

            String title = currentNode.path("title").asText();
            String image = currentNode.path("image").asText();
            String releaseYear = currentNode.path("releaseYear").asText();


            JsonNode jsonNode = currentNode.path("genre");
            List<String> genre = Arrays.asList(currentNode.path("genre").asText());

            System.out.println("title = " + title);
            System.out.println("image = " + image);
            System.out.println("releaseYear = " + releaseYear);
            System.out.println("genre = " + genre.toString());

            try {

                table.putItem(new Item().with("title", title)
                        .with("image", image)
                        .with("releaseYear", currentNode.path(releaseYear).toString())
                );

            } catch (Exception e) {
                System.err.println("Unable to add movie: " + title + " " + title);
                System.err.println(e.getMessage());
                break;
            }
        }
        try {
            parser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "true";
    }


}
