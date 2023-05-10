package ibf2022.batch1.csf.assessment.server.services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.Constants;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;;

@Service
public class MovieService {
	@Autowired
	MovieRepository movieRepo;

	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	public List<Review> searchReviews(@RequestParam String query) {

		// 1. Build the URL
		String url = UriComponentsBuilder.fromUriString(Constants.API_URL)
			.queryParam("query", query)
			.queryParam("api-key", Constants.API_KEY)
			.toUriString();

		// 2. Make the request 
		RequestEntity<Void> req = RequestEntity
				.get(url)
				.accept(MediaType.APPLICATION_JSON)
				.build();

		// 3. Make call to NYT API
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
		System.out.printf("Status code: %s\n", resp.getStatusCode());

		// 4. Get payload 
		String payload = resp.getBody();
        System.out.printf("Payload: %s\n", payload);

		// 5. Convert payload to Review[]
		JsonReader reader = Json.createReader(new StringReader(payload));
		JsonObject jsonObject = reader.readObject();
		JsonArray jsonArray = jsonObject.getJsonArray("results"); 
		List<Review> reviews = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject reviewObject = jsonArray.getJsonObject(i);
			Review review = new Review();
			review.setTitle(reviewObject.getString("display_title"));
			review.setRating(reviewObject.getString("mpaa_rating"));
			review.setByline(reviewObject.getString("byline"));
			review.setHeadline(reviewObject.getString("headline"));
			review.setSummary(reviewObject.getString("summary_short"));
			review.setReviewURL(reviewObject.getJsonObject("link").getString("url"));
			if (!reviewObject.isNull("multimedia")) {
				review.setImage(reviewObject.getJsonObject("multimedia").getString("src"));
			}
			review.setCommentCount(movieRepo.countComments(query));
			reviews.add(review);
		}
		return reviews;
	}

}
