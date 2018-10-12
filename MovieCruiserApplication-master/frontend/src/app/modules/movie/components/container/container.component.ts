import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { HttpErrorResponse } from '@angular/common/http';
import {MovieUtility} from '../../movie-utility';

@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  @Input()
  movies: Array<Movie>;

  @Input()
  useWatchlistApi: boolean;

  isMovieDeleted: boolean = false;

  constructor(private movieService: MovieService, private activeRoute: ActivatedRoute, 
    private snackBar: MatSnackBar, private mUtil: MovieUtility) {

  }

  ngOnInit() {


  }

  addToWatchlist(movie) {
    this.movieService.addMovieToWatchlist(movie).subscribe(
      (movie) => { this.snackBar.open('Movie added to Watchlist', '', { duration: 2000 }); },
      (error) => {this.mUtil.snackBarErrorMessage(error,2000)}
    );
  }

  deletMovieFromWatchlist(movie) {
    let message = `${movie.title} deleted from watchlist`;
    let movieId = movie.id;
    this.movieService.deletMovieFromWatchlist(movie).subscribe(
      (movie) => {
        this.snackBar.open(message, '', { duration: 2000 });
        for (var i = 0; i < this.movies.length; i++) {
          if (this.movies[i].id == movieId) {
            this.movies.splice(i, 1);
          }
        }
      },
      (error) => {this.mUtil.snackBarErrorMessage(error,2000)}
    );
  }
}
