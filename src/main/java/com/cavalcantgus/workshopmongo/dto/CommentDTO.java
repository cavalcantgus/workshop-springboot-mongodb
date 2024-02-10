package com.cavalcantgus.workshopmongo.dto;

import java.util.Date;

// Classe para encapsular informações de comentários em um post
public class CommentDTO {
	
	private String text;
	private Date date;
	private AuthorDTO author;
	
	// Construtor padrão
	public CommentDTO() {}

	// Construtor com argumentos
	public CommentDTO(String text, Date date, AuthorDTO author) {
		this.text = text;
		this.date = date;
		this.author = author;
	}

	// Getters & Setters
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

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}
	
}
