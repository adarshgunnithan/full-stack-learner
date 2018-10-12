package com.stackroute.moviecruiserserverapplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@Column(name = "id")
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;
	@Column(name = "comments")
	private String comments;
	@Column(name = "poster_path")
	private String posterPath;
	@Column(name = "release_date")
	private String releaseDate;
	@Column(name = "vote_Average")
	private double voteAverage;
	@Column(name = "vote_count")
	private int voteCount;

	public Movie() {
	}

	public Movie(int id, String name, String comments, String posterPath, String releaseDate, double voteAverage,
			int voteCount) {
		this.id = id;
		this.name = name;
		this.comments = comments;
		this.posterPath = posterPath;
		this.releaseDate = releaseDate;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty("title")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	@JsonProperty("poster_path")
	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
	@JsonProperty("release_date")
	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	@JsonProperty("vote_average")
	public double getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(double voteAverage) {
		this.voteAverage = voteAverage;
	}
	@JsonProperty("vote_count")
	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", comments=" + comments + ", posterPath=" + posterPath
				+ ", releaseDate=" + releaseDate + ", voteAverage=" + voteAverage + ", voteCount=" + voteCount + "]";
	}

}
