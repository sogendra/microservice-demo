package io.sunnydemo.ratingdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.sunnydemo.ratingdataservice.models.Rating;
import io.sunnydemo.ratingdataservice.models.UserRating;

@RestController
@RequestMapping("/ratings-data")
public class RatingResource {

	@GetMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId){
		return new Rating(movieId, 4);
	}
	
	@GetMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId){
		
		List<Rating> ratings = Arrays.asList(
				new Rating("5", 4),
				new Rating("6", 3)
				);
				
		return new UserRating(ratings);
	}
}
