package io.sunnydemo.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.sunnydemo.moviecatalogservice.models.CatalogItem;
import io.sunnydemo.moviecatalogservice.models.Movie;
import io.sunnydemo.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	private RestTemplate restTemplate;

	private WebClient.Builder webClientBuilder;

	@Autowired
	public MovieCatalogResource(RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
		this.restTemplate = restTemplate;
		this.webClientBuilder = webClientBuilder;
	}

	@GetMapping("/{userId}")
	@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRating ratings = this.restTemplate.getForObject("http://rating-data-service/ratings-data/users/" + userId,
				UserRating.class);

		return ratings.getUserRating().stream().map(rating -> {
			Movie movie = this.restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(),
					Movie.class);

			return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
		}).collect(Collectors.toList());
	}

	public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId){
		return Arrays.asList(new CatalogItem("No movie","", 0));
	}

	/*
	 * @GetMapping("/{userId}") public List<CatalogItem>
	 * getCatalog(@PathVariable("userId") String userId) {
	 * 
	 * List<Rating> ratings = Arrays.asList(new Rating("1234", 4), new
	 * Rating("5678", 3));
	 * 
	 * return ratings.stream().map(rating -> { //Async way of alling api using
	 * WebClientBuilder insted of syncronous RestTemplate Movie movie =
	 * this.webClientBuilder.build() .get() .uri("http://localhost:7071/movies/"
	 * + rating.getMovieId()) .retrieve() .bodyToMono(Movie.class) .block();
	 * 
	 * return new CatalogItem(movie.getName(), "desc", rating.getRating());
	 * }).collect(Collectors.toList());
	 * 
	 * }
	 */
}
