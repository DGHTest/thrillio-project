package com.semanticsquare.thrillio.entities;

import java.util.Arrays;

import com.semanticsquare.thrillio.constants.MovieGenre;

public class Movie extends Bookmark {
	private int realeaseYear;
	private String[] cast, directors;
	private MovieGenre genre;
	private double imdbRating;

	public int getRealeaseYear() {
		return realeaseYear;
	}
	public void setRealeaseYear(int realeaseYear) {
		this.realeaseYear = realeaseYear;
	}

	
	public String[] getCast() {
		return cast;
	}
	public void setCast(String[] cast) {
		this.cast = cast;
	}

	
	public String[] getDirectors() {
		return directors;
	}
	public void setDirectors(String[] directors) {
		this.directors = directors;
	}

	
	public MovieGenre getGenre() {
		return genre;
	}
	public void setGenre(MovieGenre genre) {
		this.genre = genre;
	}

	
	public double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}
	@Override
	public String toString() {
		return "Movie [realeaseYear=" + realeaseYear + ", cast=" + Arrays.toString(cast) + ", directors="
				+ Arrays.toString(directors) + ", genre=" + genre + ", imdbRating=" + imdbRating + "]";
	}
	@Override
	public boolean isKidFriendlyEligible() {
		if (genre.equals(MovieGenre.HORROR) || genre.equals(MovieGenre.THRILLERS)) {
			return false;
		}
		return true;
	}

}
