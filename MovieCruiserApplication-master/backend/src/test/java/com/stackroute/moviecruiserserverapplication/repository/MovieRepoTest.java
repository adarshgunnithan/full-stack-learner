package com.stackroute.moviecruiserserverapplication.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import com.stackroute.moviecruiserserverapplication.domain.Movie;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class MovieRepoTest {
	@Autowired
	private transient MovieCrusierRepository repo;

	public void setRepo(final MovieCrusierRepository repo) {
		this.repo = repo;
	}

	@Test
	public void testSaveMovie() throws Exception {
		repo.save(new Movie(1, "superman", "good Movie", "www.abc.com", "2015-03-23", 45.5, 112));
		final Movie movie = repo.getOne(1);
		assertThat(movie.getId()).isEqualTo(1);
	}

	@Test
	public void testUpdateMovie() throws Exception {
		repo.save(new Movie(1, "superman", "good Movie", "www.abc.com", "2015-03-23", 45.5, 112));
		final Movie movie = repo.getOne(1);
		assertEquals(movie.getName(), "superman");
		movie.setComments("hi");
		repo.save(movie);
		final Movie tempMovie = repo.getOne(1);
		assertEquals("hi", tempMovie.getComments());
	}

	@Test
	public void testDeleteMovie() throws Exception {
		repo.save(new Movie(1, "superman", "good Movie", "www.abc.com", "2015-03-23", 45.5, 112));
		final Movie movie = repo.getOne(1);
		assertEquals(movie.getName(), "superman");
		repo.delete(movie);
		assertEquals(Optional.empty(), repo.findById(1));
	}

	@Test
	public void testGetMovie() throws Exception {
		repo.save(new Movie(1, "superman", "good Movie", "www.abc.com", "2015-03-23", 45.5, 112));
		final Movie movie = repo.getOne(1);
		assertEquals(movie.getName(), "superman");
	}

	@Test
	public void testGetAllMovies() throws Exception {
		repo.save(new Movie(1, "superman", "good Movie", "www.abc.com", "2015-03-23", 45.5, 112));
		repo.save(new Movie(2, "superman", "good Movie", "www.abc.com", "2015-03-23", 45.5, 112));
		final List<Movie> movies = repo.findAll();
		assertEquals(movies.get(0).getName(), "superman");
	}
}
