package com.stackroute.moviecruiserserverapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.moviecruiserserverapplication.domain.Movie;

public interface MovieCrusierRepository extends JpaRepository<Movie, Integer> {

	
	
}
