package com.cavalcantgus.workshopmongo.dto;

import com.cavalcantgus.workshopmongo.domain.User;

// Classe para encapsular a entidade User e facilitar a transferência de dados
public class AuthorDTO {
	
	private String id;
	private String name;
	
	// Construtor padrão
	public AuthorDTO() {}
	
	// Construtor com argumentos
	public AuthorDTO(User obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}

	// Getters & Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
