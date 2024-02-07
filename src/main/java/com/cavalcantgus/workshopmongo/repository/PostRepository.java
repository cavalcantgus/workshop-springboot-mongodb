package com.cavalcantgus.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cavalcantgus.workshopmongo.domain.Post;

// Interface responsável pelo acesso a dados da entidade User 
@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
}
