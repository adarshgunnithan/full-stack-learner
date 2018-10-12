import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';


import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { MovieDialogComponent } from '../movie-dialog/movie-dialog.component';



@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})

export class ThumbnailComponent implements OnInit {

  @Input()
  movie: Movie;

  @Input()
  useWatchlistApi: boolean;

  @Output()
  addMovie = new EventEmitter();

  @Output()
  deleteMovie = new EventEmitter();


  constructor(private movieService: MovieService, private snackBar: MatSnackBar,private matDlg: MatDialog) {
  }

  ngOnInit() {
  }

  addToWatchlist() {
    this.addMovie.emit(this.movie);    
  }

  deletMovieFromWatchlist(){
    this.deleteMovie.emit(this.movie);    
  }

  updateWatchlist(actionType){
    console.log('movie is getting updated');
    let dialogRef = this.matDlg.open(MovieDialogComponent,
    {
      width:"400px",
      data: {obj: this.movie,actionType: actionType}
    });
    console.log("open the dialog");
    dialogRef.afterClosed().subscribe((results)=>{
      console.log("this dialog was closed");
    })
  }
}
