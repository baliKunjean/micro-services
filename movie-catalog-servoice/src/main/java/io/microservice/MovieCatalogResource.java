package io.microservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.microservice.models.CatalogItem;
import io.microservice.models.Movie;
import io.microservice.models.Rating;
import io.microservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate; 
	
	@RequestMapping("/{userId}")
	public List<CatalogItem>getCatalog(@PathVariable("userId")  String userId){
		
		//RestTemplate restTemplate = new RestTemplate();
		
		
		
		/*
		 * Note : These both Rest calls are synchronous
		 * Also using Rest template we are invoking services based on name registered with Eureka server  
		 * rather than actual URL
		 * 
		 */
		
		UserRating userRating = restTemplate.getForObject("http://RATING-DATA-SERVICE/ratingsdata/users/"+userId, UserRating.class);
		
		
		List<Rating> ratings = userRating.getUserRatings();
		return ratings.stream().map(rating ->{
			
			Movie movie =restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/"+rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "test", rating.getRating());
		}
		).collect(Collectors.toList());
	}
}
