package com.stackroute.moviecruiserserverapplication.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiserserverapplication.controller.MovieServiceController;
import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.service.MovieCruiserService;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieServiceController.class)

public class MovieControllerTest {

	@Autowired
	private transient MockMvc mvc;
	@MockBean
	private transient MovieCruiserService service;
	private transient Movie movie;
	static List<Movie> movies;

	@Before
	public void SetUp() {
		movies = new ArrayList<>();
		movie = new Movie(1, "POC", "goodMovie", "ww.abc.com", "2015/03/23", 45.5, 112);
		movies.add(movie);
		movie = new Movie(2, "POC-2", "Nice Movie", "ww.cba.com", "2018/03/23", 55.9, 512);
		movies.add(movie);
	}

	@Test
	public void testSaveNewMovieSucess() throws Exception {
		movie.setComments("Not that much Good");
		when(service.saveMovie(movie)).thenReturn(true);
		mvc.perform(post("/api/movie").contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie)))
				.andExpect(status().isCreated());
		verify(service, times(1)).saveMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testUpdateMovieSucess() throws Exception {
		movie.setComments("Not so Good");
		when(service.updateMovie(movie)).thenReturn(movies.get(0));
		mvc.perform(put("/api/movie/{id}", 1).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie)))
				.andExpect(status().isOk());
		verify(service, times(1)).updateMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(service);
	}

	private static String jsonToString(final Object obj) throws JsonProcessingException {
		// TODO Auto-generated method stub
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (JsonProcessingException e) {
			// TODO: handle exception
			result = "Json Processing error";
		}
		return result;
	}

	@Test
	public void testDeleteMovieById() throws Exception {
		when(service.deleteMovieById(1)).thenReturn(true);
		mvc.perform(delete("/api/movie/{id}", 1)).andExpect(status().isOk());
		verify(service, times(1)).deleteMovieById(1);
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testFetchMovieById() throws Exception {
		when(service.getMovieById(1)).thenReturn(movies.get(0));
		mvc.perform(get("/api/movie/{id}", 1)).andExpect(status().isOk());
		verify(service, times(1)).getMovieById(1);
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testGetAllMovies() throws Exception {
		when(service.getAllMovies()).thenReturn(null);
		mvc.perform(get("/api/movie")).andExpect(status().isOk());
		verify(service, times(1)).getAllMovies();
		verifyNoMoreInteractions(service);
	}

}
