package org.jesperancinha.titletextadder.api.model;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "titles")
public class Title {

	@Id
	@Column(name = "table_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "title_text")
	private String text;

	@Column(name = "title_submission_date")
	private Date date;

	@Column(name = "title_text_score")
	private Integer score;

	public Title() {
		// Here for CDI
	}

	public Title(final String title, final String title_text) {
		this.title = title;
		this.text = title_text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
