import { NgModule, Component, OnInit, Inject } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { MovieUtility } from '../../movie-utility';

@Component({
  selector: 'movie-movie-dialog',
  templateUrl: './movie-dialog.component.html',
  styleUrls: ['./movie-dialog.component.css']
})
export class MovieDialogComponent implements OnInit {

  movie: Movie;
  comments: string;
  actionType: string;

  constructor(private snackBar: MatSnackBar, private dialogRef: MatDialogRef<MovieDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data: any, private movieService: MovieService, 
    private mUtil: MovieUtility) {
    this.movie = data.obj;
    this.actionType = data.actionType;
    this.comments = data.obj.comments;
  }

  ngOnInit() {
    console.log(this.data);
  }

  onNoClick()
  {
    this.dialogRef.close();
  }

  updateComments()
  {
    console.log("comments",this.comments);
    if(this.comments!=null && this.comments.trim().length>0){
      this.movie.comments=this.comments;
      this.dialogRef.close();
      this.movieService.updateWatchlistMovie(this.movie).subscribe(
        (movie) => {
          this.snackBar.open('Watchlist Movie updated ', '', { duration: 2000 }); 
        },
        (error) => {this.mUtil.snackBarErrorMessage(error,2000)}
      );
    }   
   }

}
