import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators/map';
import { Observable } from 'rxjs/Observable';
import { retry } from 'rxjs/operators';

import { Movie } from './movie';

@Injectable()
export class MovieService {

  apiKey: string;
  tmdbEndPoint: string;
  imagePrefix: string;
  watchlistEndpoint: string;
  searchMovieEndpoint: string;

  constructor(private http: HttpClient) {
    this.apiKey = 'api_key=6bec91735aafb39a2033375d4d1c6dec';
    this.tmdbEndPoint = 'https://api.themoviedb.org/3/movie';
    this.imagePrefix = 'https://image.tmdb.org/t/p/w500';
    this.watchlistEndpoint = 'http://localhost:8080/api/movie';
    this.searchMovieEndpoint = 'https://api.themoviedb.org/3/search/movie';
  }

  getMovies(type: string, page: number = 1): Observable<Array<Movie>> {
    const endpoint = `${this.tmdbEndPoint}/${type}?${this.apiKey}&page=${page}`;
    return this.http.get(endpoint).pipe(
      retry(3),
      map(this.pickMovieResults),
      map(this.transformPosterPath.bind(this))
    );
  }

  transformPosterPath(movies): Array<Movie> {
    return movies.map(movie => {
      movie.poster_path = `${this.imagePrefix}${movie.poster_path}`;
      return movie;
    });
  }

  pickMovieResults(response) {
    return response['results'];
  }

  searchMovies(searchKey: string, page: number = 1): Observable<Array<Movie>> {
    if (searchKey.length > 0) {
      const endpoint = `${this.searchMovieEndpoint}?${this.apiKey}&page=${page}&include_adult=true&query=${searchKey}`;
      return this.http.get(endpoint).pipe(
        retry(3),
        map(this.pickMovieResults),
        map(this.transformPosterPath.bind(this))
      );
    }
    return null;
  }

  addMovieToWatchlist(movie) {
    console.log('ami k:');
    console.log(movie);
    return this.http.post(this.watchlistEndpoint, movie);
  }

  getWatchlistedMovies(): Observable<Array<Movie>> {
    return this.http.get<Array<Movie>>(this.watchlistEndpoint);
  }

  deletMovieFromWatchlist(movie) {
    const url = `${this.watchlistEndpoint}/${movie.id}`;
    return this.http.delete(url, { responseType: 'text' });
  }

  updateWatchlistMovie(movie) {
    const url = `${this.watchlistEndpoint}/${movie.id}`;
    return this.http.put(url, movie);
  }
}
