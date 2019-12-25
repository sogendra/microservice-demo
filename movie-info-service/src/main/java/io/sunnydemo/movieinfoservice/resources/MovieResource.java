package io.sunnydemo.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.sunnydemo.movieinfoservice.models.Movie;
import io.sunnydemo.movieinfoservice.models.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieResource {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${api.key}")
	private String apiKey;

	@GetMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		//Making external API call using Movie DB online website for json(https://www.themoviedb.org/documentation/api) to get movie name and description dynamically.
		String url = "http://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
		
		MovieSummary movieSummary = restTemplate
				.getForObject(url, MovieSummary.class);
		return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
	}
}
