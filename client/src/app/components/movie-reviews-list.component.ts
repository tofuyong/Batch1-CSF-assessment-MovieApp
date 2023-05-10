import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MovieClientService } from '../service/movie-client.service';
import { Subscription } from 'rxjs';
import { Review } from '../models/Review';

@Component({
  selector: 'app-movie-reviews-list',
  templateUrl: './movie-reviews-list.component.html',
  styleUrls: ['./movie-reviews-list.component.css']
})
export class MovieReviewsListComponent implements OnInit, OnDestroy {
  movieName: string = "";
  params$!: Subscription;
  isLoading: boolean = false;
  results!: Review[];

  constructor(private activatedRoute: ActivatedRoute, private movieClientSvc: MovieClientService) { }

  ngOnInit(): void {
    this.params$ = this.activatedRoute.params.subscribe(
      (params) => {
        this.movieName = params['movie'];
        this.isLoading = true; 
        this.movieClientSvc.searchReviews(this.movieName)
          .then(
            (r) => {
              console.log("API call results r:", r);
              const reviews = r;
              this.results = [];

              for (const reviewData of reviews) {
                const review = new Review (
                  reviewData.title,
                  reviewData.rating,
                  reviewData.byline,
                  reviewData.headline,
                  reviewData.summary,
                  reviewData.reviewURL,
                  reviewData.image,
                  reviewData.commentCount
                );
                this.results.push(review);
              }
            }
          )
          .catch (
            (error) => {
              console.error("Error fetching movie reviews:", error);
              this.results = []; 
            }
          )
          .finally(() => {
            this.isLoading = false;  
          });
      }
    );
  }

  ngOnDestroy(): void { this.params$.unsubscribe(); }

}
