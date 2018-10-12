import { Injectable } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';


@Injectable()
export class MovieUtility
{
    constructor(private snackBar: MatSnackBar){}

    
  snackBarErrorMessage(error: any, tmDur: number) {
      let msg = '';      
      if(error instanceof HttpErrorResponse)
      {
        var err = error as HttpErrorResponse;
        msg = err.message;
      }
    this.snackBar.open(msg, '', { duration: tmDur });
  }
}