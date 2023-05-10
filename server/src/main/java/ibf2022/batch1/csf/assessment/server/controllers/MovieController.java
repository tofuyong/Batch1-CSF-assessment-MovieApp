package ibf2022.batch1.csf.assessment.server.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.services.MovieService;

@RestController
@RequestMapping("/api")
public class MovieController {

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);


	@Autowired
	MovieService movieService;

	// Task 3, Task 4 
	@GetMapping("/search")
	public ResponseEntity<List<Review>> searchReviews(String query) {
		logger.info("String query: " + query);
		List<Review> reviews = movieService.searchReviews(query);
		return ResponseEntity.ok(reviews);
	};

	// Task 8
	@PostMapping("/comment/{movieTitle}")
	public ResponseEntity<Comment> insertComment(@PathVariable String movieTitle, @RequestBody Comment comment) {
		Comment savedComment = movieService.insertComment(comment);
		logger.info("comment saved: " + comment);
		return ResponseEntity.ok(savedComment);  
	}
}
