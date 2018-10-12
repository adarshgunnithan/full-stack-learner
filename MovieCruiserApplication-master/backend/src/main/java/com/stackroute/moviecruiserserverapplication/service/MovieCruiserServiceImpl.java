package com.stackroute.moviecruiserserverapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.repository.MovieCrusierRepository;

@Service
public class MovieCruiserServiceImpl implements MovieCruiserService {

	private final transient MovieCrusierRepository movieRepo;

	@Autowired
	public MovieCruiserServiceImpl(MovieCrusierRepository movieRepo) {
		super();
		this.movieRepo = movieRepo;
	}

	@Override
	public boolean saveMovie(final Movie movie) throws MovieAlreadyExistsException {
		final Optional<Movie> object = movieRepo.findById(movie.getId());
		if (object.isPresent()) {
			throw new MovieAlreadyExistsException("Could not save Movie,Movie already exists!");
		}
		movieRepo.save(movie);
		return true;
	}

	@Override
	public Movie updateMovie(final Movie updateMovie) throws MovieNotFoundException {
		final Movie movie = movieRepo.findById(updateMovie.getId()).orElse(null);
		if (movie == null) {
			throw new MovieNotFoundException("Could not update Movie,Movie not found!");
		}
		movie.setComments(updateMovie.getComments());
		movieRepo.save(movie);
		return movie;
	}

	@Override
	public boolean deleteMovieById(final int id) throws MovieNotFoundException {
		final Movie movie = movieRepo.findById(id).orElse(null);
		if (movie == null) {
			throw new MovieNotFoundException("Could not delete Movie,Movie not found!");
		}
		movieRepo.delete(movie);
		return true;
	}

	@Override
	public Movie getMovieById(final int id) throws MovieNotFoundException {
		final Movie movie = movieRepo.findById(id).get();
		if (movie == null) {
			throw new MovieNotFoundException("Movie not found!");
		}
		return movie;
	}

	@Override
	public List<Movie> getAllMovies() {
		return movieRepo.findAll();
	}
}
