package com.java.team.project.maven.music.album;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class App {
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, Exception {
		try {
			InputStream input = new FileInputStream("src/MusicAlbum.json");

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			TypeFactory typeFactory = TypeFactory.defaultInstance();
			List<SongDTO> songs = objectMapper.readValue(input,
					typeFactory.constructCollectionType(ArrayList.class, SongDTO.class));
			Collections.sort(songs, new Comparator<SongDTO>() {
				public int compare(SongDTO s1, SongDTO s2) {
					int c = s1.getName().compareTo(s2.getName());
					if (c == 0)
						c = s1.getTitle().compareTo(s2.getTitle());
					if (c == 0)
						c = s1.getDuration().compareTo(s2.getDuration());
					return c;
				}
			});
			System.out.println(String.format("%3s  %-18s   %-60s %-5s", "id", "artist", "title", "duration"));
			for (SongDTO song : songs) {
				System.out.println(song);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}

}
