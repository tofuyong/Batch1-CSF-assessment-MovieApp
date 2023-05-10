import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-review',
  templateUrl: './search-review.component.html',
  styleUrls: ['./search-review.component.css']
})
export class SearchReviewComponent implements OnInit {
  form!: FormGroup

  constructor(private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.form = this.createForm();
  }

  private createForm(): FormGroup {
    return this.fb.group({
      movie: this.fb.control('', [
        Validators.required,
        (control) => {
          const value = control.value?.trim();
          return value.length < 2 ? { minLengthError: true } : null;
        }
      ]) 
    }); 
  }

  search() {
    const movieName = this.form.value['movie'];
    console.info("Movie Name: ", movieName); // testing
    this.router.navigate(['/list', movieName]);
  }
}
