package com.cavalcantgus.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cavalcantgus.workshopmongo.domain.Post;
import com.cavalcantgus.workshopmongo.repository.PostRepository;
import com.cavalcantgus.workshopmongo.services.exception.ObjectNotFoundException;

// Classe que detém a lógica de negócios para as operações CRUD no banco de dados
@Service
public class PostService {
	
	// Injeção de dependência automática de PostRepository
	@Autowired
	private PostRepository postRepository;
	
	// Método para retornar um post com base em id
	public Post findById(String id) {
		Optional<Post> obj = postRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Não encontrado"));
	}	
}
