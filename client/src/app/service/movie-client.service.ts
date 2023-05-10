import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { Review } from '../models/Review';

@Injectable({
  providedIn: 'root'
})
export class MovieClientService {
  private API_URL = "api/search";

  constructor(private httpClient: HttpClient) { }

  searchReviews(movieName: string): Promise<any> {
    const params = new HttpParams().set("query", movieName);
    return lastValueFrom(this.httpClient
      .get<Review[]>(this.API_URL, {params: params}));
  }
}
