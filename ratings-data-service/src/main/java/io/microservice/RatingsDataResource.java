package io.microservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.microservice.models.Rating;
import io.microservice.models.UserRating;

@RestController
@RequestMapping("ratingsdata")
public class RatingsDataResource {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		
		Rating rating = new Rating(movieId, 4);
		
		return rating;
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		
		List<Rating> ratings = new ArrayList<Rating>();
 		
		Rating r1 = new Rating("100", 4);
		Rating r2 = new Rating("200", 4);
		Rating r3 = new Rating("300", 4);
		
		ratings.add(r1);
		ratings.add(r2);
		ratings.add(r3);
		
		
		UserRating userRating = new UserRating();
		userRating.setUserRatings(ratings);
		return userRating;
	}

}
