package com.cavalcantgus.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cavalcantgus.workshopmongo.domain.Post;

// Interface responsável pelo acesso a dados da entidade User 
@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	// Faz uma consulta no banco de dados por um post contendo uma palavra chave
	List<Post> findByTitleContaining(String text);
}
