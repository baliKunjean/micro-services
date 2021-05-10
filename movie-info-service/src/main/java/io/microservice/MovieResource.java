package io.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.microservice.models.Movie;
import io.microservice.models.MovieSummary;

@RestController
@RequestMapping("movies")
public class MovieResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.key}")
	private String apiKey;
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		
		//https://api.themoviedb.org/3/movie/100?api_key=cea3b7a0b210db1ea9f3707365849dd8 
		//Getting JSON response for this URL
		
		
		MovieSummary summary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId +"?api_key="+apiKey, MovieSummary.class);
		
		Movie movie = new Movie(movieId, summary.getTitle());
		
		return movie;
		
	}

}
