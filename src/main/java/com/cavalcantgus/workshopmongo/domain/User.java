package com.cavalcantgus.workshopmongo.domain;

import java.util.Objects;

// Entidade Usuário
public class User {
	private String id;
	private String name;
	private String email;
	
	// Construtor padrão
	public User() {}

	// Construtor com argumentos
	public User(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	// Getter & Setters
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
}
