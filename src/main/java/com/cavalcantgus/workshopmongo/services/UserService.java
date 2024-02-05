package com.cavalcantgus.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cavalcantgus.workshopmongo.domain.User;
import com.cavalcantgus.workshopmongo.dto.UserDTO;
import com.cavalcantgus.workshopmongo.repository.UserRepository;
import com.cavalcantgus.workshopmongo.services.exception.ObjectNotFoundException;

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
	
	// Método para retornar um usuário com base em id
	public User findById(String id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Não encontrado"));
	}
	
	// Método para inserir um novo usuário
	public User insert(User obj) {
		return userRepository.insert(obj);
	}
	
	// Método para deletar um usuário
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	// Converte um UserDTO para User
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
