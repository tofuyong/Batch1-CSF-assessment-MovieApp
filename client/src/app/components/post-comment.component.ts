import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { MovieClientService } from '../service/movie-client.service';
import { Comment } from '../models/Comment';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css']
})
export class PostCommentComponent implements OnInit, OnDestroy {
  params$!: Subscription;
  title!: string;
  movie!: string;
  commentForm!: FormGroup;

  reviewerName!: string;
  rating!: string;
  comments!: string;

  constructor(private activatedRoute: ActivatedRoute, private fb: FormBuilder,
              private movieClientSvc: MovieClientService, private router: Router) { }

  ngOnInit(): void {
    this.commentForm = this.createForm();
    this.params$ = this.activatedRoute.params.subscribe(
      (params) => {
        this.title = params['title'];
        this.movie= params['movie'];
      }
    );

  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control('', [ Validators.required, Validators.minLength(3) ] ),
      rating: this.fb.control('', [ Validators.required ] ),
      comments: this.fb.control('', [ Validators.required ])
    }); 
  }

  postComment() {
    const c = {} as Comment;
    c.movieTitle = this.title;
    console.log('C.movietitle', c.movieTitle); // testing
    c.reviewerName = this.commentForm?.value['reviewerName'];
    c.rating = this.commentForm?.value['rating'];
    c.comments = this.commentForm?.value['comments'];
    
    // for testing purposes
    this.movieClientSvc.saveComment(c).subscribe(
      response => console.log('Comment saved successfully', response),
      error => console.log('Error while saving comment', error)
    );

    console.info("Comment saved");
    this.router.navigate(['/list', this.movie]);
  }


  ngOnDestroy(): void { this.params$.unsubscribe(); }


}
