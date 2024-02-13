package com.cavalcantgus.workshopmongo.services;

import java.util.Date;
import java.util.List;
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
	
	// Retorna um post a partir de um título chave
	public List<Post> findByTitle(String text){
		return postRepository.findByTitle(text);
	}
	
	// Retorna um post a partir de vários critérios de busca
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24*60*60*1000); 
		return postRepository.fullSearch(text, minDate, maxDate);
	}
}
