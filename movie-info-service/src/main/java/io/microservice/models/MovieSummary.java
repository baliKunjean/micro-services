package io.microservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieSummary {
	
	@JsonProperty("original_title")
	private String title;
	
	@JsonProperty("overview")
	private String overview;
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
