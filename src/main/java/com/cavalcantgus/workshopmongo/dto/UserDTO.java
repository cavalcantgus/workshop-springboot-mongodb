package com.cavalcantgus.workshopmongo.dto;

import com.cavalcantgus.workshopmongo.domain.User;

// Classe para encapsular informações da entidade User e facilitar a transferência de dados
public class UserDTO {
	
	private String id;
	private String name;
	private String email;
	
	// Construtor padrão
	public UserDTO() {}

	// Construtor com argumentos
	public UserDTO(User obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
