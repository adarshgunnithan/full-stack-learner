import { Component, OnInit } from '@angular/core';

import { ActivatedRoute } from '@angular/router'

import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { MovieUtility } from '../../movie-utility';


@Component({
  selector: 'movie-tmdb-container',
  templateUrl: './tmdb-container.component.html',
  styleUrls: ['./tmdb-container.component.css']
})
export class TmdbContainerComponent implements OnInit {

  movies: Array<Movie>;
  movieType: string;

  constructor(private movieService: MovieService, private activeRoute: ActivatedRoute,private mUtil: MovieUtility) { 
    this.movies=[];    
    this.activeRoute.data.subscribe(data => {
      this.movieType = data.movieType
    });
  }

  ngOnInit() {  
    
    this.movieService.getMovies(this.movieType)
    .subscribe((movies) => {
      this.movies.push(...movies);
      },
      (error) => {this.mUtil.snackBarErrorMessage(error,2000)});  
  }

}
