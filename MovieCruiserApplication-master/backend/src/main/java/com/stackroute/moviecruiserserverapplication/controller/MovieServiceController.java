package com.stackroute.moviecruiserserverapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.service.MovieCruiserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api/movie")
public class MovieServiceController {

	private MovieCruiserService movieService;

	@Autowired
	public MovieServiceController(final MovieCruiserService movieService) {
		this.movieService = movieService;
	}

	@PostMapping
	public ResponseEntity<?> saveNewMovie(@RequestBody final Movie movie) {
		ResponseEntity<?> responseEntity;
		try {
			movieService.saveMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		} catch (MovieAlreadyExistsException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}",
					HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable("id") final Integer id, @RequestBody Movie movie) {
		ResponseEntity<?> responseEntity;
		try {
			final Movie fetchedMovie = movieService.updateMovie(movie);
			responseEntity = new ResponseEntity<Movie>(fetchedMovie, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}",
					HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable("id") final int id) {
		ResponseEntity<?> responseEntity;
		try {
			movieService.deleteMovieById(id);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}",
					HttpStatus.NOT_FOUND);
		}
		responseEntity = new ResponseEntity<String>("movie deleted successfully", HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> fetchMovieById(@PathVariable("id") final int id) {
		ResponseEntity<?> responseEntity;
		Movie thisMovie = null;
		try {
			thisMovie = movieService.getMovieById(id);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		responseEntity = new ResponseEntity<Movie>(thisMovie, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping
	public ResponseEntity<List<Movie>> fetchAllMovies() {
		return new ResponseEntity<List<Movie>>(movieService.getAllMovies(), HttpStatus.OK);
	}

}
