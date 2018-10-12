import { Component, OnInit } from '@angular/core';

import { Movie } from '../../movie';
import { MovieService } from '../../movie.service'
import { MovieUtility } from '../../movie-utility';

@Component({
  selector: 'movie-movie-search',
  templateUrl: './movie-search.component.html',
  styleUrls: ['./movie-search.component.css']
})
export class MovieSearchComponent implements OnInit {

  movies: Array<Movie>;
  constructor(private movieService: MovieService, private mUtil: MovieUtility) { }

  ngOnInit() {
  }

  onEnter(searchKey)
  {
    this.movieService.searchMovies(searchKey).subscribe(
      (movies) => {
        this.movies = movies;
      },
      (error) => {this.mUtil.snackBarErrorMessage(error,2000)}
    );
  }

}
