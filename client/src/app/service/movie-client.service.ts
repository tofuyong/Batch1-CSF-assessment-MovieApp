import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, lastValueFrom, of, tap } from 'rxjs';
import { Review } from '../models/Review';
import { Comment } from '../models/Comment';

@Injectable({
  providedIn: 'root'
})
export class MovieClientService {
  private API_URL_SEARCH = "api/search";
  private API_URL_COMMENT = "api/comment"; // ???

  constructor(private httpClient: HttpClient) { }

  searchReviews(movieName: string): Promise<any> {
    const params = new HttpParams().set("query", movieName);
    return lastValueFrom(this.httpClient
      .get<Review[]>(this.API_URL_SEARCH, {params: params}));
  }

  saveComment(c:Comment): Observable<Comment>{
    // const body = JSON.stringify(c); // converts JS object to Json string to send as req body to server
    return this.httpClient
      .post<Comment>(this.API_URL_COMMENT + "/" + c.movieTitle, c)
      .pipe(tap(_ => console.log('sending post request to server side to save comment')),
      catchError(this.handleError<Comment>('save comment for movie: ' + c.movieTitle)));
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      return of(result as T);
    };
  }


}
