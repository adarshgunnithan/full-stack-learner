import { Component, OnInit } from '@angular/core';

import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import {MovieUtility} from '../../movie-utility';


@Component({
  selector: 'movie-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

  movies: Array<Movie>;
  useWatchlistApi = true;
  constructor(private movieService: MovieService,private mUtil: MovieUtility) { 
    this.movies=[]; 
  }

  ngOnInit() {
    this.movieService.getWatchlistedMovies().subscribe(
      (movies) => {
        console.log(movies);
        this.movies.push(...movies);
      },
      (error) => {this.mUtil.snackBarErrorMessage(error,2000)}
    );
  }

}
