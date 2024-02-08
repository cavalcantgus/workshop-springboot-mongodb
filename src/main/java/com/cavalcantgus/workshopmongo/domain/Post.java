package com.cavalcantgus.workshopmongo.domain;

import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cavalcantgus.workshopmongo.dto.AuthorDTO;

// Entidade Post
@Document
public class Post {
	
	@Id
	private String id;
	private Date date;
	private String title;
	private String subtitle;
	private AuthorDTO author;
	
	// Construtor padrão
	public Post() {}

	// Construtor com argumentos
	public Post(String id, Date date, String title, String subtitle, AuthorDTO author) {
		this.id = id;
		this.date = date;
		this.title = title;
		this.subtitle = subtitle;
		this.author = author;
	}

	// Getters & Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	
	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	// Comparação de objetos com HashCode & Equals
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(id, other.id);
	}
	
}
