package com.cavalcantgus.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cavalcantgus.workshopmongo.domain.User;
import com.cavalcantgus.workshopmongo.repository.UserRepository;

// Classe que detém a lógica de negócios para as operações CRUD no banco de dados
@Service
public class UserService {
	
	// Injeção de dependência automática de UserRepository
	@Autowired
	private UserRepository userRepository;
	
	// Método para retornar todos os usuários
	public List<User> findAll(){
		return userRepository.findAll();	
	}
}
