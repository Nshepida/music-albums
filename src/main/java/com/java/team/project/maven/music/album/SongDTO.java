package com.java.team.project.maven.music.album;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SongDTO {
	@JsonIgnoreProperties(ignoreUnknown = true)
	private int id;
	private String name;
	private String duration;
	private String title;

	@JsonCreator
	public SongDTO(
			@JsonProperty("id") int id,
			@JsonProperty("name") String name, 
			@JsonProperty("duration") String duration,
			@JsonProperty("song") String title) {
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDuration() {
		return duration;
	}
	
	public String getTitle() {
		return title;
	}
	
	@Override
	public String toString() {

		return String.format("%3d. %-18s - %-60s %-5s", id, name, title, duration);
	}
	
}
