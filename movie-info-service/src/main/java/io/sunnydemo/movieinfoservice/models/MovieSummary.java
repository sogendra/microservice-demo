package io.sunnydemo.movieinfoservice.models;

public class MovieSummary {

	private int id;
	private String title;
	private String overview;

	public MovieSummary() {
		super();
	}

	public MovieSummary(int id, String title, String overview) {
		super();
		this.id = id;
		this.title = title;
		this.overview = overview;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

}
