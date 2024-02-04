package com.cavalcantgus.workshopmongo.services.exception;

public class ObjectNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	// Trata exceções para objetos não encontrados durante as requisições HTTP GET
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
}
