package ibf2022.batch1.csf.assessment.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.services.MovieService;

@RestController
@RequestMapping("/api")
public class MovieController {

	@Autowired
	MovieService movieService;

	// TODO: Task 3, Task 4, Task 8
	@GetMapping("/search")
	public ResponseEntity<List<Review>> searchReviews(String query) {
		List<Review> reviews = movieService.searchReviews(query);
		return ResponseEntity.ok(reviews);
	}
}
